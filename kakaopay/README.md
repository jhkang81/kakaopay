1. uid 생성서 DB 시쿼스를 사용하고나 고유 값을 사용해야 하나  시분초+랜덤숫자사용으로 중복방지 하였습니다.
2. 금액 및 값에 대한 여러 가지 변수 값 검증이 필요합니다(시간과 여건상 기본적인 값검증만 실시 하였습니다)
3. 총 결재 금액은 모두 취소되었으나 부가가치세의 경우 취소로직 등을 반영하여 일정 소액(결제금액 취소 중 부가가치세 취소금액을 적개 요청한경우)의 잔액 등이 남은경우 발생 합니다
4. 일반적으로 부가가치세는 상품에 대한 세금인데 상품에 대한 금액이 0이여도 부가가치세 일부만으로도 결제나 청구 요청이 발생하는지 고민하였습니다
요청 성공 : 0000
그외에 값을 요청 실패

- 프레임워크 : String boot
- DB : mysql
- 테이블 스크립트 : table_script.txt

- test case
- 결재요청
 : http://127.0.0.1/card/setPaying?cardNo=1234567890123456&payment=11000&mmyy=09&cvc=123&installment=0
- 결재 부분, 전체취소
 : http://localhost/card/setCancel?uid=[결제요청UID]&payment=1100&vat=100
- 결제 요청 정보
 : http://localhost/card/getPayment?uid=[응답UID]
