// A class representing the important metadata of a file.
// Used to store info about files and compare if two files
// are equal.
class FileStructure(
    private val name: String,
    private val size: Long,
    private val compressedSize: Long
) {
    // Used to write FileStructures to the output file.
    override fun toString(): String = "Name: $name, Size: $size, CompressedSize: $compressedSize"

    // Used to compare if two files are similar.
    override fun equals(other: Any?): Boolean =
        other is FileStructure
                && name == other.name
                && size == other.size
                && compressedSize == other.compressedSize

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + size.hashCode()
        result = 31 * result + compressedSize.hashCode()
        return result
    }
}