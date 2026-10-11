class Solution:
    def isValidSudoku(self, board: list[list[str]]) -> bool:
        row = [set() for i in range(9)]
        col = [set() for i in range(9)]
        boxes = [set() for i in range(9)]
        for r in range(9):
            for c in range(9):
                num = board[r][c]
                if (num == "."):
                    continue
                box = (r//3)*3+c//3
                if (num in row[r] or num in col[c] or num in boxes[box]):
                    return False
                else:
                    row[r].add(num)
                    col[c].add(num)
                    boxes[box].add(num)
        return True