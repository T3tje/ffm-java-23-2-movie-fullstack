package de.neuefische.backend.model;

public record Image(String id,
                    int width,
                    int height,
                    String url,
                    Caption caption) {
}
