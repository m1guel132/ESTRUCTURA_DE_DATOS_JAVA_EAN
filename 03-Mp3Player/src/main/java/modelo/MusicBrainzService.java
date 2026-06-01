package modelo;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servicio para consultar la API pública de MusicBrainz.
 * Busca grabaciones filtradas por género/etiqueta.
 *
 * @author migue
 */
public class MusicBrainzService {

    private static final String BASE_URL   = "https://musicbrainz.org/ws/2/recording/";
    private static final String USER_AGENT = "Mp3PlayerJava/1.0.0 (proyecto-universitario@correo.com)";
    private static final int    MAX_LIMIT  = 10;

    // ── Clase interna que representa una canción recomendada ─────
    public static class Recomendacion {
        public final String titulo;
        public final String artista;
        public final String album;
        public final String generos;

        public Recomendacion(String titulo, String artista, String album, String generos) {
            this.titulo  = titulo;
            this.artista = artista;
            this.album   = album;
            this.generos = generos;
        }
    }

    /**
     * Busca grabaciones en MusicBrainz por género (tag).
     *
     * @param genero  Género musical. Si es vacío o "Desconocido", usa "pop".
     * @return        List con hasta MAX_LIMIT recomendaciones. Nunca null.
     */
    public List<Recomendacion> buscarPorGenero(String genero) {

        List<Recomendacion> lista = new ArrayList<>();

        String generoLimpio    = normalizarGenero(genero);
        String queryCodificada = URLEncoder.encode("tag:" + generoLimpio, StandardCharsets.UTF_8);
        String url = BASE_URL + "?query=" + queryCodificada + "&limit=" + MAX_LIMIT + "&fmt=json";

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .header("Accept",     "application/json")
                    .header("User-Agent", USER_AGENT)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                lista = parsearRespuesta(response.body());
            } else {
                System.err.println("[MusicBrainz] Error HTTP: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("[MusicBrainz] Error de conexión: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        return lista;
    }

    // ── Parseo del JSON de respuesta ─────────────────────────────
    private List<Recomendacion> parsearRespuesta(String json) {

        List<Recomendacion> lista = new ArrayList<>();

        try {
            JSONObject respuesta  = new JSONObject(json);
            JSONArray  recordings = respuesta.getJSONArray("recordings");

            for (int i = 0; i < recordings.length(); i++) {
                JSONObject track = recordings.getJSONObject(i);

                String titulo = track.optString("title", "Título desconocido");

                String artista = "Artista desconocido";
                if (track.has("artist-credit") && !track.getJSONArray("artist-credit").isEmpty()) {
                    artista = track.getJSONArray("artist-credit")
                                   .getJSONObject(0)
                                   .optString("name", artista);
                }

                String album = "Álbum desconocido";
                if (track.has("releases") && !track.getJSONArray("releases").isEmpty()) {
                    album = track.getJSONArray("releases")
                                 .getJSONObject(0)
                                 .optString("title", album);
                }

                StringBuilder sb = new StringBuilder();
                if (track.has("tags")) {
                    JSONArray tags = track.getJSONArray("tags");
                    for (int j = 0; j < tags.length(); j++) {
                        sb.append(tags.getJSONObject(j).getString("name")).append(", ");
                    }
                }
                String generos = sb.length() > 0
                        ? sb.substring(0, sb.length() - 2)
                        : "Sin etiquetas";

                lista.add(new Recomendacion(titulo, artista, album, generos));
            }

        } catch (Exception e) {
            System.err.println("[MusicBrainz] Error al parsear JSON: " + e.getMessage());
        }

        return lista;
    }

    // ── Normalización del género ──────────────────────────────────
    private String normalizarGenero(String genero) {
        if (genero == null || genero.isBlank() || genero.equalsIgnoreCase("Desconocido")) {
            return "pop";
        }
        String limpio = genero.split("[/\\\\|,]")[0].trim().toLowerCase();
        return limpio.isEmpty() ? "pop" : limpio;
    }
}