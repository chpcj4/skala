"""
* 작성자: 정누리
* 작성일: 2025-03-06
* 작성목적: 리스트에서 중복된 숫자를 제거하고 정렬
"""

def set_sort(numbers):
    '''주어진 리스트에서 중복을 제거하고 정렬해서 반환하는 코드이다.'''
    numbers = list(set(numbers))
    numbers.sort()
    return numbers

NUMS = [4, 2, 2, 8, 3, 3, 1, 100, 101, 121, 31, 31, 13, 15, 13, 15, 7, 7, 77, 131, 542, 542]
print(set_sort(NUMS))
