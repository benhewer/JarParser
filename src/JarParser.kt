import java.util.zip.ZipFile;

// Finds the JAR file specified by the filepath, and extracts
// metadata of each file, returning a list of FileStructures.
// Returns null if the JAR file doesn't exist.
fun parseJar(jarFilePath: String): List<FileStructure>? {
    val jarFile = try {
        ZipFile(jarFilePath)
    } catch (_: Exception) {
        return null
    }

    val fileStructures = jarFile
        .entries()
        .toList()
        // Remove build-specific files
        .filterNot { entry ->
            entry.name.startsWith("META-INF/")
        }
        // Convert each entry into a FileStructure
        .map { entry ->
            FileStructure(
                entry.name,
                entry.size,
                entry.compressedSize
            )
        }
    jarFile.close()
    return fileStructures
}