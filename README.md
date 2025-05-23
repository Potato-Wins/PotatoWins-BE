# 🐟 스마트 양식장 이상 감지 및 실시간 시각화 시스템

**프로젝트 기간**: 2024.09 ~ 2024.12  
**팀명**: 감자로 우주정복 (PotatoWins)  
**기여도**: AI 데이터 처리 및 모니터링 알고리즘 설계, ELK 연동 및 대시보드 구현

---

## 🔎 프로젝트 개요

AI 기반 수질 및 어류 상태 모니터링 시스템을 구축하여 스마트 양식장의 운영 효율을 극대화하고, 실시간 감시 및 이상 감지 기능을 제공합니다.

- IoT 센서를 통해 수온, 염도, pH, DO 데이터를 실시간 수집
- YOLO 기반 어류 객체 인식 및 폐사 상태 분석
- Kibana 대시보드를 통한 데이터 시각화 및 자동 알림
- ELK Stack 및 Spring Boot 연동을 통한 실시간 대응 시스템 구현


https://github.com/user-attachments/assets/3eaeda11-74be-4577-b520-2e180d662aff


---

## 🎯 주요 목표

- **수질 환경 자동 모니터링** 및 임계값 초과 시 알림/대응
- **어류 객체 식별 및 이동 분석** (YOLO 기반)
- **폐사 어류 상태 분류** 및 경고 시스템
- **RFID + 영상 기반 어류 개체 추적**
- **Kibana 대시보드를 통한 관리자용 UI 제공**

---

## 🧠 AI & 시스템 구조

### 🔸 어류 객체 분류 (YOLO 기반)
- YOLOv5를 활용한 어류 객체 위치 및 크기 탐지
- 이동 경로 및 속도 추적

### 🔸 폐사 어류 탐지 (YOLO + CNN)
- 색상 및 형상을 기반으로 폐사 상태 추정
- CNN으로 세밀한 형태 분석

### 🔸 환경 모니터링 알고리즘
- 기준 임계값 초과 시 알림 발송
- 열평형 공식을 활용한 자동 온도 조절 기능 내장

---

## 🖥️ 시스템 아키텍처

<img width="669" alt="image" src="https://github.com/user-attachments/assets/06df9258-03b5-46d3-adfd-3f374c12980e" />

```plaintext
IoT 센서 데이터 수집
    ↓
Logstash → Elasticsearch → Kibana
    ↓                       ↑
Spring Boot 서버 ← 알림 시스템 (pH, 온도, 폐사 감지 등)
    ↓
React 기반 사용자 대시보드
```

## 📊 대시보드 미리보기

**ELK Stack 기반 Kibana 시각화 대시보드를 통해 실시간 수질 상태 및 어류 상태 확인 가능**

- 수온, 염도, pH 등 시간별 변화 시각화  
- 어류 수/폐사 감지 결과 시각화  
- 사용자별 알림 로그 제공  

---

## 🧑‍💻 역할 분담 (Team: 감자로 우주정복)

| 역할    | 이름(팀원)   | 주요 업무                                                                                     | GitHub |
|------------|--------------|-------------------------------------------------------------------------|--------|
| 팀장, BE   | **김민선** | 환경 데이터 처리 및 분석, ELK stack 서버 연동, 환경 지표 모니터링 알고리즘, YOLO 기반 모델 구성 | [sunnyanna0](https://github.com/sunnyanna0) |
| FE   | **김유민** | 대시보드 UI 설계, ELK 클라우드 배포, Kibana 대시보드 설계                                     | [youmeanit](https://github.com/youmeanit)       |
| BE   | **박채현** | 환경 데이터 처리 및 분석, ELK 데이터 적재, 서버 배포                                           | [chaehyeo-n](https://github.com/chaehyeo-n)       |
| FE   | **서은정** | 대시보드 UI 설계, 실시간 API 연동, 어류 객체 분류 기술 설계                                    | [enunsnv](https://github.com/enunsnv)       |


---

## 🛠️ 사용 기술

- **Backend**: Spring Boot, Python  
- **Frontend**: React, Kibana  
- **AI 모델**: YOLOv5, CNN  
- **DevOps**: Docker, ELK Stack (Elasticsearch, Logstash, Kibana)  
- **DB/Storage**: PostgreSQL, Private Cloud  
- **기타**: RFID, Ngrok, RESTful API  

---

## ✨ 기대 효과

- **운영 효율성 증가**: 자동화된 모니터링 및 경고 시스템  
- **비용 절감**: 조기 이상 감지로 폐사율 감소  
- **생산성 향상**: 실시간 데이터 기반 의사결정  
- **지속 가능한 스마트 양식 시스템 실현**  

---

## 📌 기타

- 해당 프로젝트는 **공주대학교** 및 **바인드소프트**의 멘토링을 바탕으로 진행되었습니다.  
- 향후 실증 테스트는 **제주도 스마트 양식장**과 연계하여 추진 예정입니다.

