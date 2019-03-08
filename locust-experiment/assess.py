import csv

with open('retask_distribution.csv', mode='r') as csv_file:
    csv_reader = csv.DictReader(csv_file)
    for row in csv_reader:
        name = row['Name']
        if name == 'Total':
            continue

        time95 = int(row['95%'])
        print('{}: {}, assessment: {}'.format(name, time95, 'TOO SLOW!!!' if time95 > 1000 else 'ok'))
