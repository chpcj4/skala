#--------------------------------------------------------
# 작성자: 1기 1반 정누리
# 작성목적: student의 점수 분석
# 작성일: 2025-03-04
#--------------------------------------------------------

students = [
    {"name": "Alice", "scores": {"math": 85, "science": 90, "english": 88}},
    {"name": "Bob", "scores": {"math": 78, "science": 85, "english": 92}},
    {"name": "Charlie", "scores": {"math": 92, "science": 88, "english": 95}},
    {"name": "David", "scores": {"math": 76, "science": 82, "english": 79}},
    {"name": "Eve", "scores": {"math": 89, "science": 94, "english": 87}}
]

class Student:
    def __init__(self, name, scores):
        self.name = name
        self.scores = scores

    def getScore(self, subject):
        return self.scores[subject]

    def getAvg(self):
        sum = 0
        for score in self.scores.values():
            sum += score
        return sum / len(self.scores)

# 과목별 평균 계산 함수
def subjectAvg(students_list, print=True):
    subjects_dict = {}

    for student in students_list:
        for subject, score in student.scores.items():
            if subject not in subjects_dict:
                subjects_dict[subject] = 0
            subjects_dict[subject] += score
    
    for subject in subjects_dict:
        subjects_dict[subject] /= len(students_list)

    # 각 과목별 평균 점수 출력
    if print:
        print("========================================")
        print("각 과목별 평균 점수")
        for subject, avg_score in subjects_dict.items():
            print(f"* {subject} : {avg_score:.2f}점")
        print("========================================")
    
    return subjects_dict

# 평균점수 가장 높은 과목과, 가장 낮은 과목 출력
def highAndLow(students_list):
    # 각 과목별 평균 점수 반환하는 함수
    subject_avg = subjectAvg(students_list, print=False)

    max_subject = None
    max_score = 0
    min_subject = None
    min_score = 100
    for subject, score in subject_avg.items():
        if max_score <= score:
            max_subject = subject
            max_score = score
        if min_score >= score:
            min_subject = subject
            min_score = score
    
    print("========================================")
    print(f"* 평균점수가 가장 높은 과목: {max_subject}, {max_score}점")
    print(f"* 평균점수가 가장 낮은 과목: {min_subject}, {min_score}점")
    print("========================================")

    return max_subject, max_score, min_subject, min_score

# 각 학생별 평균 점수 계산, 1등&꼴등 출력
def studentAvg(students_list):
    max_stu = None
    max_avg = 0
    min_stu = None
    min_avg = 100

    print("========================================")
    for student in students_list:
        avg = student.getAvg()
        print(f"* {student.name} : {avg:.2f}")
        if avg >= max_avg:
            max_stu = student.name
            max_avg = avg
        if avg <= min_avg:
            min_stu = student.name
            min_avg = avg
    print()
    print(f"* 1등 : {max_stu}")
    print(f"* 꼴등 : {min_stu}")
    print("========================================")

# 90점 이상인 과목이 하나라도 있는 학생들의 이름
def getStudentsWithHighScores(students_list, target_score):
    students = []
    for student in students_list:
        for score in student.scores.values():
            if score >= target_score:
                students.append(student.name)
                break
    
    return(students)

# 모든 과목 점수가 80점 이상인 학생들의 이름
def getStudentsWithAllSubjectsAboveTargetScore(students_list, target_score):
    students = []
    for student in students_list:
        flag = True
        for score in student.scores.values():
            if score < target_score:
                flag = False
                break
        if flag:
            students.append(student.name)
    
    return(students)

# 성적 데이터를 CSV 파일로 저장
def saveCSV(students_list, file_name):
    import csv

    with open(file_name, "w", encoding="utf-8", newline="") as file:
        writer = csv.writer(file)

        writer.writerow(["name", "math", "science", "english"])
        for student in students_list:
            new_list = []
            new_list.append(student.name)
            for score in student.scores.values():
                new_list.append(score)
            writer.writerow(new_list)
        print(f"{file_name} 파일을 저장했습니다.")

# CSV 파일 열기
def openCSV(file_name):
    import csv

    with open(file_name, "r", encoding="utf-8") as file:
        reader = csv.reader(file)
        subjects = next(reader)
        subjects.pop(0)
        new_students_list = [Student(row[0], {subjects[i]:row[i+1] for i in range(3)}) for row in reader]

    return new_students_list

# 각 과목별 성적 가장 좋은 학생
def bestStudentForEachSubject(students_list):
    best_student_dict = {}
    
    for student in students_list:
        for subject, score in student.scores.items():
            if subject not in best_student_dict or best_student_dict[subject][0] < score:
                best_student_dict[subject] = (score, student.name)
    
    print("========================================")
    for subject, (score, student) in best_student_dict.items():
        print(f"* {subject}과목 1등: {student} (점수: {score})")
    print("========================================")

def main():
    students_list = [Student(student["name"], student["scores"]) for student in students]
    # subject_avg = subjectAvg(students_list)
    # _, _, _, _ = highAndLow(students_list)
    # studentAvg(students_list)
    print("90점 이상인 과목이 하나라도 있는 학생들의 이름 : ", getStudentsWithHighScores(students_list, 90))
    print("모든 과목 점수가 80점 이상인 학생들의 이름 : ", getStudentsWithAllSubjectsAboveTargetScore(students_list, 80))
    saveCSV(students_list, "student_scores.csv")
    bestStudentForEachSubject(openCSV("student_scores.csv"))

if __name__ == "__main__":
    main()