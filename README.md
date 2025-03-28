# My Fullstack Project - game history info

## 🖥 Frontend (Vite + React + TypeScript)
- 위치: `history_front_*/`
- 구성: 관리자페이지(tui_grid 라이브러리 사용), 사용자페이지(React로 구현)
- 주요 기능: 리스트 형태로 턴 정보 확인, 정보 수정, csv 파일 업로드, 신규 정보 추천 형식(준비중)

## 🧰 Backend (Spring Boot)
- 위치: `ensthistory-app/`
- 주요 API: 리스트 턴 정보 CRUD, csv 파일 입력, SPP 조회(준비중)

## 📦 실행 방법
1. 백엔드 실행
```bash
cd ensthistory-app
./gradlew bootRun
```
2. 프론트 실행
```bash
cd history_front_tui
npm install
npm run dev
```