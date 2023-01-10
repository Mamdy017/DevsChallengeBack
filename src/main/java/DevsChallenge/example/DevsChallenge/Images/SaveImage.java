package DevsChallenge.example.DevsChallenge.Images;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

public class SaveImage {

    public static String localhost = "http://127.0.0.1/";
    public static String serveruser = localhost + "DesCiwara/Images/";

    public static String Userlocation = "C:/xampp/htdocs/DesCiwara/Images";

    public static String save(MultipartFile file, String nomFichier) {
        String src = "";
        String server = "";
        String location = "";

            location = Userlocation;
            server = serveruser;


        /// debut de l'enregistrement
        try {
            int index = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");

            Path chemin = Paths.get(Userlocation);
            if (!Files.exists(chemin)) {
                // si le fichier n'existe pas deja
                Files.createDirectories(chemin);
                Files.copy(file.getInputStream(), chemin
                        .resolve(nomFichier.substring(0, file.getOriginalFilename().length()-0).toLowerCase()));
                src = server + nomFichier
                        + file.getOriginalFilename().substring(0, file.getOriginalFilename().length()-0).toLowerCase();
            } else {
                // si le fichier existe pas deja
                String newPath = location + nomFichier
                        .substring(0, file.getOriginalFilename().length()-0).toLowerCase();
                Path newchemin = Paths.get(newPath);
                if (!Files.exists(newchemin)) {
                    // si le fichier n'existe pas deja
                    Files.copy(file.getInputStream(), chemin
                            .resolve(
                                    nomFichier .substring(0, file.getOriginalFilename().length()-0).toLowerCase()));
                    src = server + nomFichier .substring(0, file.getOriginalFilename().length()-0).toLowerCase();
                } else {
                    // si le fichier existe pas deja on le suprime et le recrèe

                    Files.delete(newchemin);

                    Files.copy(file.getInputStream(), chemin
                            .resolve(
                                    nomFichier .substring(index).toLowerCase()));
                    src = server + nomFichier
                            +file.getOriginalFilename().substring(index).toLowerCase();
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
            src = null;
        }

        return src;
    }

}