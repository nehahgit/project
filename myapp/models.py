from django.db import models

# Create your models here.


class login(models.Model):
    username=models.CharField(max_length=50)
    password=models.CharField(max_length=30)
    type=models.CharField(max_length=30)

class user(models.Model):
    name = models.CharField(max_length=30)
    email = models.CharField(max_length=30)
    address = models.CharField(max_length=50)
    city = models.CharField(max_length=50)
    district = models.CharField(max_length=50)
    pin = models.CharField(max_length=10)
    phone = models.CharField(max_length=10)
    LOGIN=models.ForeignKey(login,on_delete=models.CASCADE)

class institute(models.Model):
    manager=models.CharField(max_length=100,default=1)
    name=models.CharField(max_length=50)
    email=models.CharField(max_length=30)
    city=models.CharField(max_length=50)
    district=models.CharField(max_length=50)
    pin=models.CharField(max_length=10)
    phone=models.CharField(max_length=12)
    image = models.CharField(max_length=200)
    website=models.CharField(max_length=500,default=1)
    status=models.CharField(max_length=10)
    reason = models.CharField(max_length=200, default=1)
    LOGIN = models.ForeignKey(login, on_delete=models.CASCADE)

class course(models.Model):
    course_name=models.CharField(max_length=50)
    course_fee=models.CharField(max_length=30)
    course_duration=models.CharField(max_length=20,default=1)
    class_duration=models.CharField(max_length=20)
    available_seats=models.CharField(max_length=10)
    INSTITUTE=models.ForeignKey(institute,on_delete=models.CASCADE)

# class schedule(models.Model):
#     date = models.CharField(max_length=10)
#     time = models.CharField(max_length=10)
#     COURSE = models.ForeignKey(course, on_delete=models.CASCADE)
#     INSTITUTE = models.ForeignKey(institute, on_delete=models.CASCADE)

class batch(models.Model):
    batch_name = models.CharField(max_length=30)
    no_of_seats = models.CharField(max_length=10)
    date=models.CharField(max_length=20)
    time1=models.CharField(max_length=20)
    time2=models.CharField(max_length=20)
    COURSE = models.ForeignKey(course, on_delete=models.CASCADE)
    INSTITUTE = models.ForeignKey(institute, on_delete=models.CASCADE)

class student(models.Model):
    name = models.CharField(max_length=30)
    email = models.CharField(max_length=30)
    house_name = models.CharField(max_length=50)
    city = models.CharField(max_length=50)
    district = models.CharField(max_length=50)
    pin = models.CharField(max_length=10)
    phone = models.CharField(max_length=10)
    COURSE = models.ForeignKey(course, on_delete=models.CASCADE)
    INSTITUTE = models.ForeignKey(institute, on_delete=models.CASCADE)
    BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
    USER = models.ForeignKey(user, on_delete=models.CASCADE)

class customer(models.Model):
    name = models.CharField(max_length=30)
    email = models.CharField(max_length=30)
    house_name = models.CharField(max_length=50)
    city = models.CharField(max_length=50)
    district = models.CharField(max_length=50)
    pin = models.CharField(max_length=10)
    phone = models.CharField(max_length=10)
    LOGIN = models.ForeignKey(login, on_delete=models.CASCADE)

class payment(models.Model):
    date = models.CharField(max_length=10)
    amount = models.CharField(max_length=20)
    payment_type = models.CharField(max_length=20)
    CUSTOMER = models.ForeignKey(customer, on_delete=models.CASCADE)
    # STUDENT=models.ForeignKey(student,on_delete=models.CASCADE)



# class std_complaint(models.Model):
#     date = models.CharField(max_length=10)
#     compalint = models.CharField(max_length=100)
#     reply = models.CharField(max_length=100)
#     STUDENT = models.ForeignKey(student, on_delete=models.CASCADE)

class insti_gallery(models.Model):
    image1 = models.CharField(max_length=500,default=0)
    image2 = models.CharField(max_length=500,default=0)
    image3 = models.CharField(max_length=500,default=0)
    INSTITUTE = models.ForeignKey(institute, on_delete=models.CASCADE)


class shop(models.Model):
    owner=models.CharField(max_length=50,default=1)
    shop_name = models.CharField(max_length=50)
    city = models.CharField(max_length=30)
    district = models.CharField(max_length=30)
    pin=models.CharField(max_length=6,default=1)
    phone = models.CharField(max_length=12)
    email = models.CharField(max_length=30)
    image=models.CharField(max_length=500,default=1)
    shop_web=models.CharField(max_length=500,default=1)
    status = models.CharField(max_length=10)
    reason=models.CharField(max_length=200,default=1)
    LOGIN = models.ForeignKey(login, on_delete=models.CASCADE)


class category(models.Model):
    category_name = models.CharField(max_length=20)
    SHOP=models.ForeignKey(shop,on_delete=models.CASCADE,default=1)


class material(models.Model):
    name=models.CharField(max_length=30)
    rent_amount=models.CharField(max_length=30)
    description=models.CharField(max_length=50)
    image=models.CharField(max_length=200,default=0)
    SHOP = models.ForeignKey(shop, on_delete=models.CASCADE)
    CATEGORY=models.ForeignKey(category,on_delete=models.CASCADE)


class instrument(models.Model):
    name = models.CharField(max_length=30)
    rent_amount = models.CharField(max_length=30)
    desc=models.CharField(max_length=200)
    imageinstru=models.CharField(max_length=200,default=0)
    SHOP = models.ForeignKey(shop, on_delete=models.CASCADE)
    CATEGORY = models.ForeignKey(category, on_delete=models.CASCADE)

class instrument_request(models.Model):
    date = models.CharField(max_length=20)
    from_date=models.CharField(max_length=20,default=1)
    to_date=models.CharField(max_length=20,default=1)
    total_amount=models.CharField(max_length=50,default=1)
    status = models.CharField(max_length=20)
    USER = models.ForeignKey(user, on_delete=models.CASCADE)
    INSTRUMENT = models.ForeignKey(instrument, on_delete=models.CASCADE)


class material_request(models.Model):
    date = models.CharField(max_length=20)
    from_date = models.CharField(max_length=20, default=1)
    to_date = models.CharField(max_length=20, default=1)
    total_amount = models.CharField(max_length=50, default=1)
    status = models.CharField(max_length=20)
    USER = models.ForeignKey(user, on_delete=models.CASCADE)
    MATERIAL = models.ForeignKey(material, on_delete=models.CASCADE)



class return_entry(models.Model):
    item_name=models.CharField(max_length=100)
    issue_date=models.CharField(max_length=20)
    return_date=models.CharField(max_length=20)
    amount = models.CharField(max_length=20)



class complaint(models.Model):
    date=models.CharField(max_length=10)
    compalint=models.CharField(max_length=100)
    reply=models.CharField(max_length=100)
    LOGIN=models.ForeignKey(login,on_delete=models.CASCADE,default=1)



class shop_gallery(models.Model):
    image1=models.CharField(max_length=500,default=0)
    image2=models.CharField(max_length=500,default=0)
    image3=models.CharField(max_length=500,default=0)
    SHOP=models.ForeignKey(shop,on_delete=models.CASCADE,default=0)

class review(models.Model):
    review=models.CharField(max_length=100)
    USER = models.ForeignKey(user, on_delete=models.CASCADE)


class shopreview(models.Model):
    review=models.CharField(max_length=100)
    date=models.CharField(max_length=15,default=1)
    USER = models.ForeignKey(user, on_delete=models.CASCADE)
    SHOP = models.ForeignKey(shop, on_delete=models.CASCADE)

class instireview(models.Model):
    date=models.CharField(max_length=13,default=1)
    review=models.CharField(max_length=100)
    USER = models.ForeignKey(user, on_delete=models.CASCADE)
    INSTITUTE = models.ForeignKey(institute, on_delete=models.CASCADE)



class review_st(models.Model):
    review=models.CharField(max_length=100)
    STUDENT = models.ForeignKey(student, on_delete=models.CASCADE)


class requst(models.Model):
    requ=models.CharField(max_length=200)
    date=models.CharField(max_length=20)
    status=models.CharField(max_length=20)
    CUSTOMER = models.ForeignKey(customer, on_delete=models.CASCADE)
    MATERIAL = models.ForeignKey(material, on_delete=models.CASCADE)
    INSTRUMENT = models.ForeignKey(instrument, on_delete=models.CASCADE)

class course_requst(models.Model):
    date = models.CharField(max_length=20)
    status = models.CharField(max_length=20)
    USER = models.ForeignKey(user, on_delete=models.CASCADE,default=1)
    INSTITUTE = models.ForeignKey(institute, on_delete=models.CASCADE,default=1)

class bank(models.Model):
    cnumber=models.CharField(max_length=50)
    cvv=models.CharField(max_length=50)
    # ifsc=models.CharField(max_length=50)
    holder=models.CharField(max_length=50)
    balance=models.CharField(max_length=50)
    exp_date=models.CharField(max_length=50,default=1)



class app_rating(models.Model):
    rating=models.CharField(max_length=10)
    date=models.CharField(max_length=15)
    USER = models.ForeignKey(user, on_delete=models.CASCADE)

class instru_payment(models.Model):
    date = models.CharField(max_length=10)
    amount = models.CharField(max_length=20)
    payment_type = models.CharField(max_length=20)
    REQUEST = models.ForeignKey(instrument_request, on_delete=models.CASCADE)
    USER=models.ForeignKey(user,on_delete=models.CASCADE,default=1)

class material_payment(models.Model):
    date = models.CharField(max_length=10)
    amount = models.CharField(max_length=20)
    payment_type = models.CharField(max_length=20)
    REQUEST = models.ForeignKey(material_request, on_delete=models.CASCADE)
    USER=models.ForeignKey(user,on_delete=models.CASCADE,default=1)


# class coursebooking(models.Model):
#     booking_date = models.CharField(max_length=20)
#     payment_type = models.CharField(max_length=20)
#     # PAYMENT=models.ForeignKey(payment,on_delete=models.CASCADE)
#     COURSE = models.ForeignKey(course, on_delete=models.CASCADE)
#     STUDENT = models.ForeignKey(student, on_delete=models.CASCADE)

class rent(models.Model):
    issue_date=models.CharField(max_length=20)
    return_date=models.CharField(max_length=20)
    amount=models.CharField(max_length=20)
    INSTRUMENT = models.ForeignKey(instrument, on_delete=models.CASCADE)
    MATERIAL = models.ForeignKey(material, on_delete=models.CASCADE)
    # PAYMENT = models.ForeignKey(payment, on_delete=models.CASCADE)
    USER = models.ForeignKey(user, on_delete=models.CASCADE)

