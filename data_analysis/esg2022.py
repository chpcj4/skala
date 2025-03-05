'''
* SKALA 1기
* 작성팀: IMAX
* 작성목적: ESG 추이 분석
* 작성일: 2025-03-05
'''

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error

FILE_PATH = "ESG_2022.csv"

# 데이터 불러오기
encodings = [
    None,
    "utf-8",
    "cp949",
    "euckr",
]  # 기본 인코딩(None) → utf-8 → cp949 → euckr 순으로 시도
for encoding in encodings:
    try:
        df = pd.read_csv(FILE_PATH, encoding=encoding)
        ENCODING_NAME = encoding if encoding else "default"
        print(f"파일을 {ENCODING_NAME} 인코딩으로 성공적으로 읽었습니다.")
        break  # 성공하면 반복문 종료
    except UnicodeDecodeError:
        ENCODING_NAME = encoding if encoding else "default"
        print(f"{ENCODING_NAME} 인코딩 실패. 다른 인코딩 시도 중...")

# 데이터 기본 정보 확인
print("\n* 데이터프레임에 대한 정보")
print(df.info())
print("\n* 데이터프레임의 첫 5행")
print(df.head())
print("\n* 데이터프레임의 통계 정보")
print(df.describe())
print("\n* NAN 값이 있는지 확인")
print(df.isnull().sum())

# 데이터셋 설정 (독립변수 1개, 종속변수 여러 개)
X = df[["year"]]  # 독립변수: 'year'
y = df[
    [
        "Total Emissions",
        "Net Emissions",
        "Energy",
        "fuel combustion",
        "Dealing",
        "Carbon dioxide transport and storage",
        "mineral industry",
        "chemical industry",
        "metal industry",
        "non-energy fuels",
        "Electronics Industry",
        "ozone-depleting",
    ]
]  # 종속변수들

# Train, Test 나누기
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

# 모델 훈련 (다중 출력 회귀)
model = LinearRegression()
model.fit(X_train, y_train)
print("\n>>>>>>모델 훈련을 완료했습니다.<<<<<<")

# 예측
y_pred = model.predict(X_test)

# MSE와 RMSE 계산
mse_list = []
rmse_list = []

for idx, column in enumerate(y_test.columns):
    mse = mean_squared_error(y_test.iloc[:, idx], y_pred[:, idx])
    rmse = np.sqrt(mse)
    mse_list.append(mse)
    rmse_list.append(rmse)

# 결과를 DataFrame으로 저장
results_df = pd.DataFrame(
    {"Column": y_test.columns, "MSE": mse_list, "RMSE": rmse_list}
)

# 결과 출력
print("\n* 모델 평가(MSE, RMSE)")
print(results_df)

# 향후 5년의 'year' 값 생성
last_year = df["year"].max()  # 마지막 연도
future_years = np.arange(last_year + 1, last_year + 6)  # 다음 5년

# 미래 데이터의 독립변수 (연도) 준비
future_X = pd.DataFrame(future_years, columns=["year"])

# 모델을 사용하여 향후 5년의 예측값 계산
future_y_pred = model.predict(future_X)

# 예측값을 DataFrame으로 변환
future_df = pd.DataFrame(future_y_pred, columns=y_test.columns)
future_df["year"] = future_years  # 연도 추가

# 색상 리스트 (각 컬럼에 대해 고유한 색상 할당)
colors = [
    "r",
    "b",
    "g",
    "c",
    "m",
    "y",
    "orange",
    "purple",
    "brown",
    "pink",
    "gray",
    "green",
]

# 그래프 크기 설정
plt.figure(figsize=(10, 5))

# 기존의 데이터를 기반으로 그래프 그리기 (투명도 적용)
for idx, column in enumerate(y.columns):
    plt.plot(
        df["year"],
        df[column],
        marker="o",
        linestyle="--",
        color=colors[idx],
        label=f"Original {column}",
        alpha=0.5,
    )

    # 추세선 추가 (선형 회귀로 추세선 생성)
    p = np.polyfit(df["year"], df[column], 1)  # 1차 함수로 추세선 피팅
    trend_line = np.polyval(p, df["year"])  # 추세선 계산
    plt.plot(
        df["year"], trend_line, linestyle="--", color=colors[idx], alpha=0.3
    )  # 추세선 그리기

# 예측된 값을 기존 그래프에 추가 (불투명하게 표시)
for idx, column in enumerate(future_df.columns[:-1]):  # 'year'은 제외
    plt.plot(
        future_df["year"],
        future_df[column],
        marker="*",
        linestyle="--",
        color=colors[idx],
        label=f"Predicted {column}",
        alpha=1.0,
    )

# 제목과 레이블 추가
plt.title("Yearly Emissions and Industry Data with 5-Year Forecast")
plt.xlabel("Year")
plt.ylabel("Value")

# 범례 추가 (왼쪽으로 이동하고, 원본과 예측값을 세로로 구분)
plt.legend(loc="center left", bbox_to_anchor=(1, 0.5), ncol=1, title="Data Type")

# 눈금 설정
plt.xticks(rotation=45)
plt.grid(True)

# 그래프 표시 및 저장 (bbox_inches='tight' 추가)
IMAGE_FILE_PATH = "esg_2022_with_forecast_and_trends.jpg"
plt.savefig(IMAGE_FILE_PATH, dpi=300, bbox_inches="tight")
