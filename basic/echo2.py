def main():
    while True:  # 무한 반복
        sentence = input("문장을 입력하세요 (!quit 입력 시 종료): ")  # 사용자 입력 받기
        if sentence == "!quit":  # 사용자가 !quit 입력하면
            print("프로그램을 종료합니다.")  
            break  # 반복문 종료
        print("입력한 문장:", sentence)  # 입력한 문장 출력

if __name__ == "__main__":
    main()