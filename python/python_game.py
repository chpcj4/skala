import os
import pygame
import numpy as np

os.environ["SDL_VIDEODRIVER"] = "dummy"

# 색상 정의
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
GRAY = (200, 200, 200)
BLUE = (0, 0, 255)
RED = (255, 0, 0)

# 게임 설정
WIDTH, HEIGHT = 450, 450
GRID_SIZE = 9
CELL_SIZE = WIDTH // GRID_SIZE

# 초기 스도쿠 보드 (0은 빈칸)
initial_board = [
    [5, 3, 0, 0, 7, 0, 0, 0, 0],
    [6, 0, 0, 1, 9, 5, 0, 0, 0],
    [0, 9, 8, 0, 0, 0, 0, 6, 0],
    [8, 0, 0, 0, 6, 0, 0, 0, 3],
    [4, 0, 0, 8, 0, 3, 0, 0, 1],
    [7, 0, 0, 0, 2, 0, 0, 0, 6],
    [0, 6, 0, 0, 0, 0, 2, 8, 0],
    [0, 0, 0, 4, 1, 9, 0, 0, 5],
    [0, 0, 0, 0, 8, 0, 0, 7, 9]
]

# 현재 보드 상태 복사
board = np.array(initial_board)

# Pygame 초기화
pygame.init()
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("스도쿠 게임")
font = pygame.font.Font(None, 40)

# 선택된 셀
selected_cell = None

def draw_grid():
    """ 스도쿠 그리드 그리기 """
    for row in range(GRID_SIZE + 1):
        thickness = 3 if row % 3 == 0 else 1
        pygame.draw.line(screen, BLACK, (0, row * CELL_SIZE), (WIDTH, row * CELL_SIZE), thickness)
        pygame.draw.line(screen, BLACK, (row * CELL_SIZE, 0), (row * CELL_SIZE, HEIGHT), thickness)

def draw_numbers():
    """ 숫자 출력 """
    for row in range(GRID_SIZE):
        for col in range(GRID_SIZE):
            num = board[row][col]
            if num != 0:
                color = BLACK if initial_board[row][col] != 0 else BLUE
                text = font.render(str(num), True, color)
                screen.blit(text, (col * CELL_SIZE + 20, row * CELL_SIZE + 10))

def highlight_cell():
    """ 선택된 셀 강조 표시 """
    if selected_cell:
        x, y = selected_cell
        pygame.draw.rect(screen, RED, (x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE), 3)

def is_valid_move(row, col, num):
    """ 숫자가 유효한지 검사 """
    if num in board[row]: return False
    if num in board[:, col]: return False
    box_x, box_y = (col // 3) * 3, (row // 3) * 3
    if num in board[box_y:box_y+3, box_x:box_x+3]: return False
    return True

def main():
    global selected_cell
    running = True
    while running:
        screen.fill(WHITE)
        draw_grid()
        draw_numbers()
        highlight_cell()
        
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                x, y = event.pos
                selected_cell = (x // CELL_SIZE, y // CELL_SIZE)
            elif event.type == pygame.KEYDOWN and selected_cell:
                x, y = selected_cell
                if initial_board[y][x] == 0:
                    if event.key == pygame.K_BACKSPACE or event.key == pygame.K_0:
                        board[y][x] = 0
                    elif pygame.K_1 <= event.key <= pygame.K_9:
                        num = event.key - pygame.K_0
                        if is_valid_move(y, x, num):
                            board[y][x] = num

        pygame.display.flip()
    
    pygame.quit()

if __name__ == "__main__":
    main()
