import java.io.File

fun main(args: Array<String>) {
    if (args.size != 3) {
        println("Arguments should be:")
        println("   parse <jarFile> <outputFile>")
        println("   compare <outputFile1> <outputFile2>")
        return
    }

    val operation = args[0]
    when (operation) {
        "parse" -> parse(args[1], args[2])
        "compare" -> compare(args[1], args[2])
        else -> {
            println("First argument is not a valid operation")
            return
        }
    }
}

// Writes the metadata of each file in the given JAR file
// to the output file given.
fun parse(jarFilePath: String, outputPath: String) {
    val fileStructures = parseJar(jarFilePath)

    if (fileStructures == null) {
        println("Jar file does not exist")
        return
    }

    File(outputPath).printWriter().use { out ->
        fileStructures.forEach { fileStructure ->
            out.println(fileStructure.toString())
        }
    }
}

// Compares the similarity of two JAR files by counting the
// similar files within them. Outputs the similarity to the
// terminal.
fun compare(outputPath1: String, outputPath2: String) {
    val fileStructures1 = parseOutput(outputPath1)
    val fileStructures2 = parseOutput(outputPath2)

    if (fileStructures1 == null || fileStructures2 == null) {
        println("Output files do not exist")
        return
    }
    // Total files are the sum of unique files in output1 and output2
    // Therefore to find a similarity score, every time two files are
    // the same, it adds one to the score, and decrements the total,
    // then the final score represents a percentage of similar files.
    val fileStructures2Set = fileStructures2.toSet()
    var totalUniqueSize = fileStructures1.size + fileStructures2.size
    val similarities = fileStructures1.sumOf { fileStructure1 ->
        if (fileStructure1 in fileStructures2Set) {
            totalUniqueSize--
            1L
        } else {
            0L
        }
    }
    val similarityScore = similarities.toDouble() / totalUniqueSize * 100

    println("$similarities out of $totalUniqueSize files are similar")
    println("Similarity: ${"%.1f".format(similarityScore)}%")
}