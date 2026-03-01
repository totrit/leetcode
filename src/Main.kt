import course_schedule.Solution

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val solution = Solution()

    print(
        solution.canFinish(7, arrayOf(
            intArrayOf(1,0),
            intArrayOf(0,3),
            intArrayOf(0,2),
            intArrayOf(3,2),
            intArrayOf(2,5),
            intArrayOf(4,5),
            intArrayOf(5,6),
            intArrayOf(2,4),
        ))
    )
}