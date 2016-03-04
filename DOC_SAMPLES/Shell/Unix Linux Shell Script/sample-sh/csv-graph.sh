#!/bin/sh
 
#(1)
csvfile="data.csv"   # 자료 CSV 파일
GRAPH_WIDTH=50       # 그래프 너비
 
#(2)
markprint() {
  local i=0  #(3)
  while [ $i -lt $1 ]  #(4)
  do
    /bin/echo -n "*"  #(5)
    i=$(expr $i + 1)
  done
}
 
# 자료에서 최댓값 얻음.역순 정렬해서 첫 줄 얻음
#(6)
max=$(awk -F, '{print $3}' "$csvfile" | sort -nr | head -n 1)

# 자료가 모두 0이면 최댓값을 1로 지정
#(7)
if [ $max -eq 0 ]; then
  max=1
fi

# CSV파일을 읽어서 값마다 그래프 출력
#(8)
while IFS=, read id name score
do
  markprint $(expr $GRAPH_WIDTH \* $score / $max)  #(9)
  /bin/echo " [$name]"
done < $csvfile
