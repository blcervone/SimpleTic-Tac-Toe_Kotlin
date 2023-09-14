package tictactoe

var xCounter = 0
var oCounter = 0
var winX = 0
var winO = 0
var gameOver = false


fun printBoard(board: List<List<Char>>) {
    println("---------")
    for (index in board.indices) {
        print("| ")
        board[index].map { print("$it ") }
        println("|")
    }
    println("---------")
}
fun isValidMove(board: MutableList<MutableList<Char>>, coordinates: List<String>): Boolean {
    return board[coordinates[0].toInt() - 1][coordinates[1].toInt() - 1] == ' '
}

fun isValidCoordinate(x: Int, y: Int): Boolean {
    return x in 1..3 && y in 1 .. 3
}

fun updateBoard(board: MutableList<MutableList<Char>>, coordinates: List<String>) {
    val row = board[coordinates[0].toInt() - 1]
    if (xCounter == oCounter) {
        row[coordinates[1].toInt() - 1] = 'X'
        xCounter++
    } else if (xCounter > oCounter) {
        row[coordinates[1].toInt() - 1] = 'O'
        oCounter++
    }
}

fun checkWinConditions(board: MutableList<MutableList<Char>>) {
    if (board[0][0] == 'X' && board[0][1] == board[0][0] && board[0][2] == board[0][0]) winX++
    if (board[1][0] == 'X' && board[1][1] == board[1][0] && board[1][2] == board[1][0]) winX++
    if (board[2][0] == 'X' && board[2][1] == board[2][0] && board[2][2] == board[2][0]) winX++
    if (board[0][0] == 'X' && board[1][0] == board[0][0] && board[2][0] == board[0][0]) winX++
    if (board[0][1] == 'X' && board[1][1] == board[0][1] && board[2][1] == board[0][1]) winX++
    if (board[0][2] == 'X' && board[1][2] == board[0][2] && board[2][2] == board[0][2]) winX++
    if (board[0][0] == 'X' && board[1][1] == board[0][0] && board[2][2] == board[0][0]) winX++
    if (board[2][0] == 'X' && board[1][1] == board[2][0] && board[0][2] == board[2][0]) winX++
    if (board[0][0] == 'O' && board[0][1] == board[0][0] && board[0][2] == board[0][0]) winO++
    if (board[1][0] == 'O' && board[1][1] == board[1][0] && board[1][2] == board[1][0]) winO++
    if (board[2][0] == 'O' && board[2][1] == board[2][0] && board[2][2] == board[2][0]) winO++
    if (board[0][0] == 'O' && board[1][0] == board[0][0] && board[2][0] == board[0][0]) winO++
    if (board[0][1] == 'O' && board[1][1] == board[0][1] && board[2][1] == board[0][1]) winO++
    if (board[0][2] == 'O' && board[1][2] == board[0][2] && board[2][2] == board[0][2]) winO++
    if (board[0][0] == 'O' && board[1][1] == board[0][0] && board[2][2] == board[0][0]) winO++
    if (board[2][0] == 'O' && board[1][1] == board[2][0] && board[0][2] == board[2][0]) winO++
    if (winX == 1) {
        println("X wins")
        gameOver = true
    }
    if (winO == 1) {
        println("O wins")
        gameOver = true
    }
    if (xCounter > 4 && winX == 0) {
        println("Draw")
        gameOver = true
    }
    return
}

fun main() {
    val emptyBoard = "         "
    val board = emptyBoard.chunked(3) { row: CharSequence -> row.map { it }.toMutableList() }.toMutableList()
    printBoard(board)
    do {
        println("Make a move: ")
        val userInput = readln().split(" ")
        if(userInput[0].toIntOrNull() == null || userInput[1].toIntOrNull() == null) {
            println("You should enter numbers!")
            continue;
        }
        if(!isValidCoordinate(userInput[0].toInt(), userInput[1].toInt())) {
            println("Coordinates should be from 1 to 3!")
            continue;
        }

        if(!isValidMove(board, userInput)) {
            println("This cell is occupied! Choose another one!")
            continue;
        }
        updateBoard(board, userInput)
        printBoard(board)
        checkWinConditions(board)
    } while (!gameOver)
    return
}