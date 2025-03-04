#--------------------------------------------------------
# 작성자: 1기 1반 정누리
# 작성목적: 학생 점수 분석
# 작성일: 2025-03-04
#--------------------------------------------------------

import csv

students = [
    {"name": "Alice", "scores": {"math": 85, "science": 90, "english": 88}},
    {"name": "Bob", "scores": {"math": 78, "science": 85, "english": 92}},
    {"name": "Charlie", "scores": {"math": 92, "science": 88, "english": 95}},
    {"name": "David", "scores": {"math": 76, "science": 82, "english": 79}},
    {"name": "Eve", "scores": {"math": 89, "science": 94, "english": 87}}
]


class Student:
    """학생 정보를 저장하는 클래스"""
    def __init__(self, name, scores):
        self.name = name
        self.scores = scores

    def get_score(self, subject):
        """특정 과목의 점수를 반환"""
        return self.scores.get(subject, 0)

    def get_avg(self):
        """학생의 평균 점수를 반환"""
        return sum(self.scores.values()) / len(self.scores)


def subject_avg(students_list, display=True):
    """각 과목별 평균 점수 계산 및 출력"""
    subjects_dict = {}
    for student in students_list:
        for subject, score in student.scores.items():
            subjects_dict[subject] = subjects_dict.get(subject, 0) + score

    for subject in subjects_dict:
        subjects_dict[subject] /= len(students_list)

    if display:
        print("========================================")
        print("각 과목별 평균 점수")
        for subject, avg_score in subjects_dict.items():
            print(f"* {subject} : {avg_score:.2f}점")
        print("========================================")

    return subjects_dict


def high_and_low(students_list):
    """평균 점수가 가장 높은 과목과 낮은 과목 출력"""
    subject_avg_dict = subject_avg(students_list, display=False)
    max_subject = max(subject_avg_dict, key=subject_avg_dict.get)
    min_subject = min(subject_avg_dict, key=subject_avg_dict.get)

    print("========================================")
    print(f"* 평균점수가 가장 높은 과목: {max_subject}, {subject_avg_dict[max_subject]:.2f}점")
    print(f"* 평균점수가 가장 낮은 과목: {min_subject}, {subject_avg_dict[min_subject]:.2f}점")
    print("========================================")

    return max_subject, min_subject


def student_avg(students_list):
    """각 학생별 평균 점수를 계산하고 1등과 꼴등을 출력"""
    max_student = min_student = students_list[0].name
    max_avg = min_avg = students_list[0].get_avg()

    print("========================================")
    for student in students_list:
        avg = student.get_avg()
        print(f"* {student.name} : {avg:.2f}")
        if avg > max_avg:
            max_student, max_avg = student.name, avg
        if avg < min_avg:
            min_student, min_avg = student.name, avg
    print()
    print(f"* 1등 : {max_student}")
    print(f"* 꼴등 : {min_student}")
    print("========================================")


def get_students_with_high_scores(students_list, target_score):
    """특정 점수 이상을 받은 학생 목록 반환"""
    return [student.name for student in students_list if any(score >= target_score for score in student.scores.values())]


def get_students_with_all_subjects_above(students_list, target_score):
    """모든 과목에서 특정 점수 이상을 받은 학생 목록 반환"""
    return [student.name for student in students_list if all(score >= target_score for score in student.scores.values())]


def save_csv(students_list, file_name):
    """학생 성적 데이터를 CSV 파일로 저장"""
    with open(file_name, "w", encoding="utf-8", newline="") as file:
        writer = csv.writer(file)
        writer.writerow(["name", "math", "science", "english"])
        for student in students_list:
            writer.writerow([student.name] + list(student.scores.values()))
    print(f"{file_name} 파일을 저장했습니다.")


def open_csv(file_name):
    """CSV 파일을 읽고 Student 객체 리스트 반환"""
    with open(file_name, "r", encoding="utf-8") as file:
        reader = csv.reader(file)
        subjects = next(reader)[1:]  # 첫 행에서 과목명 추출
        return [Student(row[0], {subjects[i]: int(row[i+1]) for i in range(len(subjects))}) for row in reader]


def best_student_for_each_subject(students_list):
    """각 과목에서 최고 점수를 받은 학생 출력"""
    best_students = {}
    for student in students_list:
        for subject, score in student.scores.items():
            if subject not in best_students or best_students[subject][0] < score:
                best_students[subject] = (score, student.name)

    print("========================================")
    for subject, (score, student) in best_students.items():
        print(f"* {subject} 과목 1등: {student} (점수: {score})")
    print("========================================")


def main():
    students_list = [Student(student["name"], student["scores"]) for student in students]
    print("90점 이상인 과목이 하나라도 있는 학생들의 이름 :", get_students_with_high_scores(students_list, 90))
    print("모든 과목 점수가 80점 이상인 학생들의 이름 :", get_students_with_all_subjects_above(students_list, 80))
    save_csv(students_list, "student_scores.csv")
    best_student_for_each_subject(open_csv("student_scores.csv"))


if __name__ == "__main__":
    main()
