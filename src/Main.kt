import kotlin.random.Random

const val CORRECTOR = 10000000
const val GROUP_CORDS_CORRECTOR = 10000000
const val GROUP_RADIUS = 20000 // meter
const val GROUPS_DISTANCE = 61000 // meter

fun main() {
    val images = mutableListOf<InstantImage>()
    images.addAll(randomGroupLocation(0))
    images.addAll(randomGroupLocation(3))
    images.addAll(randomGroupLocation(6))
    println(groupByTimeInterval(images).size)

}

private fun randomGroupLocation(groupCorrector: Int): List<InstantImage> {
    val images = mutableListOf<InstantImage>()

    for (i in 0..20) {
        val lat = String.format("%.7f", Random.nextDouble(groupCorrector.toDouble(),(groupCorrector + 1).toDouble())).toDouble()
        val lon = String.format("%.7f", Random.nextDouble(groupCorrector.toDouble(),(groupCorrector + 1).toDouble())).toDouble()
        println("$lat $lon")
        images.add(InstantImage("file://image$lat", "$lat", "$lon", i.toLong(), lat, lon))
    }
    return images
}

private fun groupByTimeInterval(images: List<InstantImage>): List<InstantImage> {
    if (images.isEmpty() || images.size == 1) {
        return emptyList()
    }

    val itemsGroupsByTime = mutableListOf<List<InstantImage>>()
    var itemGroups = mutableListOf<InstantImage>()
    var groupTime = images[0].dataTaken

    for (image in images) {
        if (image.dataTaken - groupTime > -1) {
            groupTime = image.dataTaken
            itemGroups = mutableListOf()
            itemsGroupsByTime.add(itemGroups)
        }
        itemGroups.add(image)
    }

    for (list in itemsGroupsByTime) {
        if (list.size > 1) {
            return list
        }
    }

    return emptyList()
}