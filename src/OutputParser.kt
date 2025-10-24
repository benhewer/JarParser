import java.io.File

// Finds the output file specified by the filepath, and extracts
// info of each line, creating a FileStructure from the info.
// Returns null if the output file doesn't exist.
fun parseOutput(outputFilePath: String): List<FileStructure>? {
    val file = File(outputFilePath)

    if (!file.exists()) {
        return null
    }

    val fileStructures = file
        .readLines()
        .map { line ->
            // Attributes is a list of key value pairs (attribute name to attribute)
            val attributes = line
                .split(", ")
                .associate { attribute ->
                    // Attribute in the form "Name: <name>", so split into key value pair
                    val (key, value) = attribute.split(": ")
                    key to value
                }
            // Find each attribute in lookup table. If any can't be found,
            // the output file is incorrect, so just return null.
            FileStructure(
                attributes["Name"] ?: return null,
                attributes["Size"]?.toLong() ?: return null,
                attributes["CompressedSize"]?.toLong() ?: return null
            )
        }
    return fileStructures
}