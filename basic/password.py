import re  # 정규 표현식을 사용하기 위한 모듈

def validate_password(password):
    # 정규 표현식 패턴 정의
    pattern = r"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
    
    # 정규 표현식 검사
    if re.match(pattern, password):
        return "유효한 비밀번호입니다."
    else:
        return "비밀번호는 최소 8자 이상이며, 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다."

# 사용자 입력 받기
password = input("비밀번호를 입력하세요: ")
print(validate_password(password))