CSV_BASE_NAME=retask

rm ${CSV_BASE_NAME}_*.csv

locust --locustfile=locustfile.py \
  --host=http://retask.me \
  --no-web \
  --clients=10 \
  --hatch-rate=1 \
  --run-time=30s \
  --csv-base-name=${CSV_BASE_NAME}

python assess.py
