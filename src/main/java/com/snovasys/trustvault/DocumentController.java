package com.snovasys.trustvault;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class DocumentController {

    private final Path uploadDir =
            Paths.get("uploads").toAbsolutePath().normalize();

    public DocumentController() throws IOException {
        Files.createDirectories(uploadDir);
    }


@GetMapping("/documents")
public String documents(Model model) throws IOException {
    try (var files = Files.list(uploadDir)) {
        model.addAttribute("documents",
                files.filter(Files::isRegularFile)
                     .map(path -> path.getFileName().toString())
                     .toList());
    }
    model.addAttribute("message", "Welcome to TrustVault Documents");
    return "documents";
}


    @PostMapping("/documents/upload")
    public String uploadDocument(
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        if (file == null || file.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    "error", "Please select a file.");
            return "redirect:/documents";
        }

        String originalName = Paths.get(
                file.getOriginalFilename() == null
                        ? "document"
                        : file.getOriginalFilename()
        ).getFileName().toString();

        if (file.getSize() > 10 * 1024 * 1024) {
            redirectAttributes.addFlashAttribute(
                    "error", "File size must not exceed 10 MB.");
            return "redirect:/documents";
        }

        String lowerName = originalName.toLowerCase();
        if (!(lowerName.endsWith(".pdf")
                || lowerName.endsWith(".txt")
                || lowerName.endsWith(".png")
                || lowerName.endsWith(".jpg")
                || lowerName.endsWith(".jpeg")
                || lowerName.endsWith(".doc")
                || lowerName.endsWith(".docx"))) {
            redirectAttributes.addFlashAttribute(
                    "error", "File type not allowed.");
            return "redirect:/documents";
        }

        String storedName = UUID.randomUUID()
                + "_" + originalName;

        try {
            Path destination = uploadDir.resolve(storedName)
                    .normalize();

            if (!destination.startsWith(uploadDir)) {
                redirectAttributes.addFlashAttribute(
                        "error", "Invalid file path.");
                return "redirect:/documents";
            }

            Files.copy(file.getInputStream(), destination);

            redirectAttributes.addFlashAttribute(
                    "success", "Document uploaded successfully!");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute(
                    "error", "Upload failed. Please try again.");
        }

        return "redirect:/documents";
    }

    @GetMapping("/documents/download/{filename:.+}")
    public ResponseEntity<byte[]> downloadDocument(
            @PathVariable String filename) {

        try {
            Path filePath = uploadDir.resolve(filename).normalize();

            if (!filePath.startsWith(uploadDir)
                    || !Files.isRegularFile(filePath)) {
                return ResponseEntity.notFound().build();
            }

            byte[] fileContent = Files.readAllBytes(filePath);

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + filename + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileContent);

        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/documents/delete/{filename:.+}")
    public String deleteDocument(
            @PathVariable String filename,
            RedirectAttributes redirectAttributes) {

        try {
            Path filePath = uploadDir.resolve(filename).normalize();

            if (!filePath.startsWith(uploadDir)
                    || !Files.isRegularFile(filePath)) {
                redirectAttributes.addFlashAttribute(
                        "error", "Document not found.");
                return "redirect:/documents";
            }

            Files.delete(filePath);

            redirectAttributes.addFlashAttribute(
                    "success", "Document deleted successfully!");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute(
                    "error", "Unable to delete document.");
        }

        return "redirect:/documents";
    }


}