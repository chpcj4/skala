"""
* 작성자: 정누리
* 작성일: 2025-03-06
* 작성목적: List와 Numpy 배열의 비교
"""

import time
import numpy as np

# 데이터 크기
SIZE = 1_000_000

# 리스트를 이용한 연산
list_data = list(range(SIZE))
start_time = time.time()
list_result = [x**2 for x in list_data]
end_time = time.time()
print(f"리스트 연산 시간: {end_time - start_time:.5f}초")

# NumPy 배열을 이용한 연산
numpy_data = np.arange(SIZE)
start_time = time.time()
numpy_result = numpy_data**2
end_time = time.time()
print(f"NumPy 연산 시간: {end_time - start_time:.5f}초")
