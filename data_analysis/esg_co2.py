'''
SKALA
작성팀: IMAX(2조)
작성목적: ESG 경영을 위한 CO2 농도 분석
작성일 : 2025-03-05
'''

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error

FILE_PATH = "ESG_CO2_2021.csv"

# 기본 인코딩(None) → utf-8 → cp949 → euckr 순으로 시도
encodings = [None, "utf-8", "cp949", "euckr"]
for encoding in encodings:
    try:
        df = pd.read_csv(FILE_PATH, encoding=encoding)
        ENCODING_NAME = encoding if encoding else "default"
        print(f"파일을 {ENCODING_NAME} 인코딩으로 성공적으로 읽었습니다.")
        break  # 성공하면 반복문 종료
    except UnicodeDecodeError:
        ENCODING_NAME = encoding if encoding else "default"
        print(f"{ENCODING_NAME} 인코딩 실패. 다른 인코딩 시도 중...")

print(df.info(), end="\n\n")
print(df.head(), end="\n\n")
print(df.describe(), end="\n\n")

print(df.isnull().sum())

# year 컬럼에 NaN 값이 있으면 해당 행 삭제
if df["year"].isnull().sum() > 0:
    df = df.dropna(subset=["year"])
    print("NaN이 포함된 'year' 행 삭제 완료")

# net emission 컬럼에 NaN 값이 있으면 앞뒤 평균으로 보간
if df["net emission"].isnull().sum() > 0:
    df["net emission"] = df["net emission"].interpolate(method="linear")
    print("NaN이 포함된 'net emission' 값 보간(앞뒤 평균) 완료")

# 그래프 그리기
plt.figure(figsize=(10, 5))  # 그래프 크기 설정
plt.plot(
    df["year"],
    df["net emission"],
    marker="*",
    linestyle="-",
    color="r",
    label="Net Emission",
)

# 그래프 제목 및 라벨 설정
plt.title("Net CO₂ Emission Over the Years")
plt.xlabel("Year")
plt.ylabel("Net Emission (tons)")

# 범례 추가
plt.legend()

# 눈금 설정
plt.xticks(rotation=45)
plt.grid(True)

# 그래프 표시
IMAGE_FILE_PATH = "esg_co2_2021.jpg"
plt.savefig(IMAGE_FILE_PATH, dpi=300)

# 데이터셋 설정
X = df[["year"]]
y = df["net emission"]

# Train, Test 나누기
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

# 모델 훈련
model = LinearRegression()
model.fit(X_train, y_train)

print("\n훈련된 모델의 기울기와 절편:")
print("* 기울기(w1): ", model.coef_[0])
print("* 절편(w0): ", model.intercept_)

# 향후 5년 동안의 연도 생성
future_years = np.arange(df["year"].max() + 1, df["year"].max() + 6).reshape(-1, 1)

# 5년 뒤 CO₂ 배출량 예측
future_predictions = model.predict(future_years)

# 결과 출력
print("\n향후 5년 CO₂ 배출량 예측 결과:")
for year, prediction in zip(future_years.flatten(), future_predictions):
    print(f"* {year}: {prediction:.2f} tons")

# 평가(MSE 이용)
y_pred = model.predict(X_test)

mse = mean_squared_error(y_test, y_pred)
print("\n평균제곱오차(MSE): ", mse)
print("평균제곱오차(RMSE): ", np.sqrt(mse))

# 선형 회귀 추세선 추가
x_range = np.arange(df["year"].min(), df["year"].max() + 1).reshape(-1, 1)
y_trend = model.predict(x_range)
plt.plot(x_range, y_trend, linestyle="--", color="lightgray", label="Trend Line")

# 예측된 미래 CO₂ 배출량 그래프 추가
plt.plot(
    future_years,
    future_predictions,
    marker="o",
    linestyle="--",
    color="b",
    label="Predicted Emission",
)

# 범례 추가
plt.legend()

# 그래프 저장
plt.savefig("esg_co2_2026_2030.jpg", dpi=300)

# 예측값을 DataFrame으로 저장
df_future = pd.DataFrame(
    {"year": future_years.flatten(), "predicted_net_emission": future_predictions}
)
df_future.to_csv("ESG_CO2_Predictions.csv", index=False, encoding="utf-8")
