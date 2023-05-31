from datetime import datetime
start_date = datetime.strptime('18/8/2008', "%d/%m/%Y")
end_date = datetime.strptime('26/8/2008', "%d/%m/%Y")
print (abs((end_date-start_date).days))