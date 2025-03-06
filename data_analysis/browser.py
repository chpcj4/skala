"""
* 작성자: 정누리
* 작성일: 2025-03-06
* 작성목적: 데코레이터를 사용해서 실행시간 측정
"""

from collections import deque


def browser_navigation(commands):
    """
    Command를 실행하는 코드
    """
    q = deque()
    answer = []
    for command in commands:
        if command == "back":
            if len(q) >= 2:
                q.pop()
                answer.append(q[-1])
            else:
                answer.append("뒤로 갈 수 없습니다!")
        else:
            q.append(command)
            answer.append(command)
    return answer


COMMANDS = ["google.com", "facebook.com", "back", "twitter.com", "back"]
print(browser_navigation(COMMANDS))
