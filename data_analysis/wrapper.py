"""
* 작성자: 정누리
* 작성일: 2025-03-06
* 작성목적: 데코레이터를 사용해서 실행시간 측정
"""

import time
import functools

# 실행시간 측정을 위한 데코레이터
def log_time(func):
    """
    함수 실행 시간을 측정하고 로그로 저장하는 데코레이터.
    """
    @functools.wraps(func)
    def wrapper():
        start_time = time.time()
        func()  # 함수 실행
        end_time = time.time()
        exec_time = end_time - start_time
        print(f"{func.__name__} 실행 시간: {exec_time:.6f} 초")

        # 로그 파일에 실행시간 기록 (UTF-8 인코딩 추가)
        with open("log.txt", "a", encoding="utf-8") as log_file:
            log_file.write(f"{func.__name__} 실행 시간: {exec_time:.6f} 초\n")

    return wrapper

# 예제 함수
@log_time
def example_function():
    """
    실행 예제 함수 (2초 대기 후 '작업 완료!' 출력).
    """
    time.sleep(2)
    print("작업 완료!")

example_function()
