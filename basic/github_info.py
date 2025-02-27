import requests

def get_github_user_info(username):
    """
    GitHub API를 사용하여 사용자의 정보를 가져오는 함수
    :param username: GitHub 계정 이름 (str)
    :return: 사용자 정보 (dict) 또는 오류 메시지 (str)
    """
    url = f"https://api.github.com/users/{username}"  # GitHub API URL
    response = requests.get(url)  # API 요청
    
    if response.status_code == 200:  # 정상 응답
        return response.json()  # JSON 데이터 반환
    elif response.status_code == 404:  # 사용자를 찾을 수 없음
        return "사용자를 찾을 수 없습니다."
    else:  # 기타 오류 처리
        return f"오류 발생: {response.status_code}"


# 정상적인 계정 테스트
print("✅ 정상 계정 테스트")
user_info = get_github_user_info("torvalds")  # 리누스 토르발즈 (Linux 창시자) GitHub 계정
print(user_info)

# 존재하지 않는 계정 테스트
print("\n❌ 비정상 계정 테스트")
user_info = get_github_user_info("thisaccountdoesnotexist123456")
print(user_info)
