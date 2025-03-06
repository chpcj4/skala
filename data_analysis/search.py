"""
* 작성자: 정누리
* 작성일: 2025-03-06
* 작성목적: 데코레이터를 사용해서 실행시간 측정
"""

import time
import functools
from collections import deque

G = {
    'A': ['B', 'C'],
    'B': ['D', 'E'],
    'C': ['F', 'G'],
    'D': [],
    'E': ['H'],
    'F': [],
    'G': [],
    'H': []
}

# 실행시간 측정을 위한 데코레이터
def log_time(func):
    """
    함수 실행 시간을 측정하고 로그로 저장하는 데코레이터.
    """
    @functools.wraps(func)
    def wrapper(*args, **kwargs):  # 매개변수를 받을 수 있도록 수정
        print(func.__name__)
        start_time = time.time()
        result = func(*args, **kwargs)  # 매개변수를 전달하여 함수 실행
        end_time = time.time()
        print()
        exec_time = end_time - start_time
        print(f"실행 시간: {exec_time:.6f} 초")

        # 로그 파일에 실행시간 기록 (UTF-8 인코딩 추가)
        with open("log.txt", "a", encoding="utf-8") as log_file:
            log_file.write(f"{func.__name__} 실행 시간: {exec_time:.6f} 초\n")

        return result  # 반환값 유지

    return wrapper

@log_time
def bfs(G, root):
    q = deque()
    q.append(root)

    while q:
        current = q.popleft()
        print(current, end='')
        for next in G[current]:
            q.append(next)

@log_time
def dfs(G, root):
    q = deque()
    q.append(root)

    while q:
        current = q.popleft()
        print(current, end='')
        for idx in range(len(G[current]) - 1, -1, -1):  # 역순 탐색 (DFS)
            q.appendleft(G[current][idx])

bfs(G, 'A')
print()
dfs(G, 'A')
