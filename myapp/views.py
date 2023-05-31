import datetime

from django.core.files.storage import FileSystemStorage
from django.http import HttpResponse,JsonResponse
from django.shortcuts import render, redirect


# Create your views here.
from myapp.models import *


def login_fun(request):
    return render(request,'index.html')


def login_post(request):
    username=request.POST['name']
    password=request.POST['pswrd']

    lobj=login.objects.filter(username=username,password=password)
    if lobj.exists():

        lobjj=login.objects.get(username=username,password=password)
        request.session['lid'] =lobjj.id
        if lobjj.type=='admin':
            return redirect('/myapp/home')
        elif lobjj.type=='institute':
            return redirect('/myapp/insti_home')
        elif lobjj.type=='shop':
            return redirect('/myapp/shop_home')
        else:
            return HttpResponse("Invalid")
    else:
        return HttpResponse("Invalid")



def home(request):
    return render(request,"ADMIN/index.html")

def view_new_institutes(request):
    res=institute.objects.filter(status="pending")
    return render(request,"ADMIN/View_institute.html",{'data':res})



def view_new_institutes_post(request):
    name=request.POST['name']
    res=institute.objects.filter(name__contains=name,status='pending')
    return render(request,"ADMIN/View_institute.html",{'data':res})






def approve_institute(request,id):
    res=institute.objects.filter(pk=id).update(status="approved")
    return HttpResponse("<script>alert('Approved successfully..');window.location='/myapp/view_new_institutes/'</script>")

def view_approved_institutes(request):
    res = institute.objects.filter(status="approved")
    return render(request,"ADMIN/View_approvdinstitutes.html",{'data':res})


def view_approved_institutes_post(request):
    name = request.POST['name']
    res = institute.objects.filter(name__contains=name, status='approved')
    return render(request, "ADMIN/View_approvdinstitutes.html", {'data': res})


def reject_institute(request,id):
    # res=institute.objects.filter(pk=id).update(status="rejected")
    # return HttpResponse("<script>alert('Rejected successfully..');window.location='/myapp/view_new_institutes/'</script>")
    return render(request,"ADMIN/reason.html",{'id':id})

def reject_institute_post(request):
    id=request.POST['id']
    reas=request.POST['reason']
    res = institute.objects.filter(pk=id).update(status="rejected",reason=reas)
    return HttpResponse("<script>alert('Rejected successfully..');window.location='/myapp/view_new_institutes/'</script>")


def view_rejected_institutes(request):
    res = institute.objects.filter(status="rejected")
    return render(request,"ADMIN/View_rejctdinstitues.html",{'data':res})

def view_rejected_institutes_post(request):
    name = request.POST['name']
    res = institute.objects.filter(name__contains=name, status='rejected')
    return render(request, "ADMIN/View_rejctdinstitues.html", {'data': res})


def view_shops(request):
    res=shop.objects.filter(status="pending")
    return render(request,"ADMIN/view_shop.html",{'data':res})


def view_shop_post(request):
    shop_names = request.POST['name']
    res = shop.objects.filter(shop_name__contains=shop_names, status='pending')
    return render(request, "ADMIN/view_shop.html", {'data': res})



def approve_shop(request,id):
    res=shop.objects.filter(pk=id).update(status="approved")
    return HttpResponse("<script>alert('Approved successfully..');window.location='/myapp/view_shops/'</script>")


def view_approved_shops(request):
    res = shop.objects.filter(status="approved")
    return render(request,"ADMIN/View_apprvdshops.html",{'data':res})

def view_approved_shops_post(request):
    shop_names = request.POST['name']
    res = shop.objects.filter(shop_name__contains=shop_names, status='approved')
    return render(request, "ADMIN/View_apprvdshops.html", {'data': res})


def reject_shop(request,id):
    # res=shop.objects.get(pk=id)
    return render(request,"ADMIN/shop_rejectreason.html",{'id':id})

def reject_shop_post(request):
    id = request.POST['id']
    reas = request.POST['reason']
    res = shop.objects.filter(pk=id).update(status="rejected", reason=reas)
    return HttpResponse("<script>alert('Rejected successfully..');window.location='/myapp/view_shops/'</script>")

def view_rejected_shops(request):
    res = shop.objects.filter(status="rejected")
    return render(request,"ADMIN/View_rejctdshops.html",{'data':res})


def view_rejected_shops_post(request):
    shop_names = request.POST['name']
    res = shop.objects.filter(shop_name__contains=shop_names, status='rejected')
    return render(request, "ADMIN/View_rejctdshops.html", {'data': res})


def view_regusers(request):
    res = user.objects.all()
    return render(request,"ADMIN/View_users.html",{'data':res})


def view_regusers_post(request):
    name = request.POST['name']
    res = user.objects.filter(name__contains=name)
    return render(request, "ADMIN/View_users.html", {'data': res})

def name_search(request):
    # id=request.POST['id']
    # us=user.objects.getname_search(pk=id)
    dist=request.POST['district']
    res=user.objects.filter(district__contains=dist)
    return render(request,'ADMIN/View_users.html',{'data':res})





def view_reviews(request):
    res=app_rating.objects.all()
    return render(request,"ADMIN/Admin_viewreviews.html",{'data':res})

def view_student_review(request):
    res=review_st.objects.all()
    return render(request,"ADMIN/Admin_view_studentreviews.html",{'data':res})

def change_pswrd(request):
    return render(request,"ADMIN/admin_changepswrd.html")

def admin_pswrd_post(request):
    admin_curnt_pswrd=request.POST['curntpswrd']
    admin_new_pswrd=request.POST['newpswrd']
    admin_confrm_pswrd=request.POST['cnfrmpswrd']
    lobj=login.objects.filter(pk=str(request.session['lid']),password=admin_curnt_pswrd)
    if lobj.exists():
        lobj=login.objects.filter(pk=str(request.session['lid'])).update(password=admin_new_pswrd)
        # lobj.password=admin_new_pswrd
        return HttpResponse("<script>alert('Updated successfully..');window.location='/myapp/login/'</script>")
    else:
        return HttpResponse("<script>alert('Invalid Credentials..');window.location='/myapp/change_pswrd/'</script>")


#============Institute===================





def insti_home(request):
    return render(request,"INSTITUTE/insti_index.html")

def institute_signup(request):
    return render(request,"INSTITUTE/signupone_index.html")

def instisignup_post(request):

    manager=request.POST['manager']
    name=request.POST['NAME']
    website=request.POST['web']
    city=request.POST['CITY']
    district=request.POST['DISTRICT']
    pin=request.POST['PIN']
    phone=request.POST['PHONE']
    email=request.POST['EMAIL']
    image=request.FILES['fileField']
    paswd=request.POST['pswrd']
    cpass=request.POST['cpassword']


    fs=FileSystemStorage()

    from datetime import datetime
    dt = 'Photo1' + datetime.now().strftime("%H%M%S-%d%m%Y") + ".jpg"
    fn = fs.save(dt,image)
    lobj=login()
    lobj.username=email
    lobj.password=paswd
    lobj.type='institute'
    lobj.save()

    iobj = institute()
    iobj.image = fs.url(dt)
    iobj.manager=manager
    iobj.name = name
    iobj.website=website
    iobj.city = city
    iobj.district=district
    iobj.pin=pin
    iobj.phone=phone
    iobj.email=email
    iobj.LOGIN=lobj
    iobj.status='pending'
    iobj.save()
    return HttpResponse("<script>alert('Account Created');window.location='/myapp/login/'</script>")

def insti_profile(request):
    res=institute.objects.get(LOGIN=request.session["lid"])
    return render(request,"INSTITUTE/view_profile.html",{'data':res})

def edit_profile(request):
    res = institute.objects.get(LOGIN=request.session["lid"])
    # res = institute.objects.get(pk=mid)
    return render(request, "INSTITUTE/insti_edit_profile.html", {'data': res})


def edit_profile_post(request):
    id = request.POST['id']
    imanager=request.POST['manager']
    name = request.POST['name']
    city = request.POST['city']
    dist = request.POST['dist']
    pin = request.POST['pin']
    phone = request.POST['ph']
    email = request.POST['email']
    if 'fileField' in request.FILES:
        image = request.FILES["fileField"]
        if image.name !="":
            fs = FileSystemStorage()
            from datetime import datetime
            s = datetime.now().strftime("%Y%m%d%H%M%S") + ".jpg"
            # s = 'Photo1' + datetime.now().strftime("%H%M%S-%d%m%Y") + ".jpg"
            fn = fs.save(s, image)
            res = institute.objects.filter(LOGIN_id=id).update(manager=imanager, name=name, city=city, district=dist,
                                                               pin=pin, phone=phone,
                                                               email=email, image=fs.url(s))
            return redirect('/myapp/insti_profile/')
        else:
            res = institute.objects.filter(LOGIN_id=id).update(manager=imanager, name=name, city=city, district=dist,
                                                               pin=pin, phone=phone,
                                                               email=email)
            return redirect('/myapp/insti_profile/')
    else:
        res = institute.objects.filter(LOGIN_id=id).update(manager=imanager, name=name, city=city, district=dist,
                                                           pin=pin, phone=phone,
                                                           email=email)
        return redirect('/myapp/insti_profile/')






def insti_add_course(request):
    return render(request,"INSTITUTE/add_course.html")

def course_post(request):
    course_name= request.POST['course_name']
    seat=request.POST['seat']
    courseduration=request.POST['course_duration']
    class_duration=request.POST['duration']
    fees=request.POST['fees']


    cobj=course()
    cobj.INSTITUTE_id=institute.objects.get(LOGIN_id=request.session['lid']).id
    cobj.course_name=course_name
    cobj.available_seats=seat
    cobj.course_fee=fees
    cobj.course_duration=courseduration
    cobj.class_duration=class_duration
    cobj.save()

    return HttpResponse("<script>alert('Course Added');window.location='/myapp/insti_add_course/'</script>")


def view_course(request):
    res = course.objects.filter(INSTITUTE_id=institute.objects.get(LOGIN_id=request.session['lid']).id)
    return render(request, "INSTITUTE/View_course.html", {'data': res})


def course_edit(request,id):
    res = course.objects.get(pk=id)
    return render(request, "INSTITUTE/edit_course.html", {'data': res})

def course_edit_post(request):
    course_name = request.POST['course_name']
    seat = request.POST['seat']
    courseduration = request.POST['course_duration']
    class_duration = request.POST['duration']
    fees = request.POST['fees']
    cid=request.POST['cid']
    res=course.objects.filter(pk=cid).update(course_name=course_name,available_seats=seat,course_duration=courseduration,class_duration=class_duration,course_fee=fees)
    return HttpResponse("<script>alert('Course Edited');window.location='/myapp/view_course/'</script>")



def insti_add_batch(request):
    cour=course.objects.filter(INSTITUTE_id=institute.objects.get(LOGIN_id=request.session['lid']).id)
    return render(request,"INSTITUTE/add_batch.html",{'course':cour})

def batch_post(request):
    batch_name=request.POST['batch_name']
    date=request.POST['date']
    time1=request.POST['time1']
    time2=request.POST['time2']
    no_seats=request.POST['no_seats2']
    cid=request.POST['crd']

    bobj=batch()
    bobj.INSTITUTE_id=institute.objects.get(LOGIN_id=request.session['lid']).id
    bobj.batch_name=batch_name
    bobj.no_of_seats=no_seats
    bobj.date=date
    bobj.time1=time1
    bobj.time2=time2
    bobj.COURSE_id=cid

    bobj.save()

    return HttpResponse("<script>alert('Batch Added');window.location='/myapp/insti_add_batch/'</script>")


def insti_view_batch(request):
    res=batch.objects.filter(INSTITUTE_id=institute.objects.get(LOGIN_id=request.session['lid']).id)
    return render(request,"INSTITUTE/View_Batch.html",{'data':res})


def batch_edit(request,id):
    res1 = course.objects.filter(INSTITUTE=institute.objects.get(LOGIN=request.session['lid']).id)
    res = batch.objects.get(pk=id)
    return render(request,"INSTITUTE/edit_batch.html",{'data': res,'data1':res1})


def batch__edit_post(request):
    id=request.POST['id']
    batch_name = request.POST['batchname']
    date = request.POST['date']
    time1 = request.POST['time1']
    time2 = request.POST['time2']
    no_seats = request.POST['no_seats2']
    course=request.POST['crd']
    res=batch.objects.filter(pk=id).update(batch_name=batch_name,date=date,time1=time1,time2=time2,no_of_seats=no_seats,COURSE_id=course)
    return HttpResponse("<script>alert('Updated');window.location='/myapp/insti_view_batch/'</script>")


def instituion_new_request(request):
  res = course_requst.objects.filter(status="pending")
  return render(request, "INSTITUTE/View_newrequst.html", {'data': res})



def accept_requ(request,id):
    res=course_requst.objects.filter(pk=id).update(status="accepted")
    return HttpResponse("<script>alert('Accepted');window.location='/myapp/instituion_new_request/'</script>")


def insti_accptd_req(request):
    res = course_requst.objects.filter(status="accepted")
    return render(request,"INSTITUTE/View_accepted_students.html",{'data':res})

def reject_requ(request,id):
    res=course_requst.objects.filter(pk=id).update(status="rejected")
    return HttpResponse("<script>alert('Rejected');window.location='/myapp/instituion_new_request/'</script>")

def insti_rejctd_req(request):
    res = course_requst.objects.filter(status="rejected")
    return render(request,"INSTITUTE/View_rejctdstudnts.html",{'data':res})

def insti_view_complaint(request):
    res=std_complaint.objects.all()
    return render(request, "INSTITUTE/View_Complaint.html", {'data':res})

def insti_sent_reply(request,mid):

    return render(request,"INSTITUTE/sent_reply.html",{'mid':mid})

def reply_post(request):
    reply=request.POST['reply']
    mid=request.POST['mid']


    robj=std_complaint.objects.get(pk=mid)
    robj.reply=reply
    robj.save()

    return HttpResponse("<script>alert('Sent')</script>")

def inst_gallery(request):
    return render(request,"INSTITUTE/gallery.html")

def gallery_post(request):
    image1=request.FILES['image1insti']
    image2=request.FILES['image3insti']
    image3=request.FILES['image4insti']
    fs=FileSystemStorage()
    fs2=FileSystemStorage()
    fs3=FileSystemStorage()

    from datetime import datetime
    dt = 'Photo1'+datetime.now().strftime("%H%M%S-%d%m%Y")+".jpg"
    dt2 = 'Photo2'+datetime.now().strftime("%H%M%S-%d%m%Y")+".jpg"
    dt3 = 'Photo3'+datetime.now().strftime("%H%M%S-%d%m%Y")+".jpg"

    fn = fs.save(dt, image1)
    fn1 = fs2.save(dt2, image2)
    fn2 = fs3.save(dt3, image3)

    galobj=insti_gallery()
    galobj.image1=fs.url(dt)
    galobj.image2=fs2.url(dt2)
    galobj.image3=fs3.url(dt3)
    galobj.INSTITUTE_id=institute.objects.get(LOGIN_id=request.session['lid']).id
    galobj.save()
    return HttpResponse("<script>alert('Success');window.location='/inst_gallery'</script>")


def insti_view_gallery(request):
    a=[]
    res=insti_gallery.objects.filter(INSTITUTE_id=institute.objects.get(LOGIN_id=request.session['lid']).id)
    for i in res:
        a.append({'im':i.image1})
    print(a[0])
    return render(request,"INSTITUTE/View_gallery.html",{'data':res,'a':a})

def insti_view_gallery_more(request):
  res=insti_gallery.objects.filter(INSTITUTE_id=institute.objects.get(LOGIN_id=request.session['lid']).id)
  print(res)
  return render(request,"INSTITUTE/view_more.html",{'data':res})

def shop_edit_gallery(request,id):
    res=insti_gallery.objects.get(pk=id)
    return render(request,'INSTITUTE/edit_gallery.html',{'data':res})

def shop_edit_gallery_post(request):
    id=request.POST['id']
    if 'image1insti'in request.FILES:
        image1 = request.FILES['image1insti']
        if image1.name !="":
            from datetime import datetime
            fs = FileSystemStorage()
            dt = 'Photo1' + datetime.now().strftime("%H%M%S-%d%m%Y") + ".jpg"
            fn = fs.save(dt, image1)
            # path=fs.save(dt)
            res = insti_gallery.objects.filter(pk=id).update(image1=fs.url(dt))
            return redirect('/myapp/insti_view_gallery_more/')
        else:
            return redirect('/myapp/insti_view_gallery_more/')

    if 'image3insti' in request.FILES:
        image2 = request.FILES['image3insti']
        if image2.name !="":
            from datetime import datetime
            fs2 = FileSystemStorage()
            dt2 = 'Photo2' + datetime.now().strftime("%H%M%S-%d%m%Y") + ".jpg"
            fn2 = fs2.save(dt2, image2)
            # path2=fs2.save(dt2)
            res = insti_gallery.objects.filter(pk=id).update(image2=fs2.url(dt2))
            return redirect('/myapp/insti_view_gallery_more/')
        else:
            return redirect('/myapp/insti_view_gallery_more/')
    if 'image3insti' in request.FILES:
        image3 = request.FILES['image4insti']
        if image3.name !="":
            from datetime import datetime
            fs3 = FileSystemStorage()
            dt3 = 'Photo3' + datetime.now().strftime("%H%M%S-%d%m%Y") + ".jpg"
            fn3 = fs3.save(dt3, image3)
            # path3=fs3.save(dt3)
            res = insti_gallery.objects.filter(pk=id).update(image3=fs3.url(dt3))
            return redirect('/myapp/insti_view_gallery_more/')
        else:
            return redirect('/myapp/insti_view_gallery_more/')
    else:
        return redirect('/myapp/insti_view_gallery_more/')


def delete_gallery(request, id):
    res = insti_gallery.objects.filter(id=id).delete()
    return HttpResponse('<script>alert("Deleted");window.location="/myapp/insti_view_gallery/"</script>')


def insti_review(request):
    res=instireview.objects.all()
    return render(request,"INSTITUTE/view_reviews.html",{'data':res})

def insti_changepswrd(request):
    return render(request,"INSTITUTE/Change_pswrd.html")

def insti_pswrd_post(request):
    insti_curnt_pswrd=request.POST['curntpswrd']
    insti_new_pswrd=request.POST['newpswrd']
    insti_confrm_pswrd=request.POST['cnfrmpswrd']
    res=login.objects.get(pk=str(request.session['lid']),password=insti_curnt_pswrd)
    if res is not None:
        login.objects.filter(pk=str(request.session['lid'])).update(password=insti_new_pswrd)

        return HttpResponse("Success")
    else:
        return HttpResponse("no")

def insti_pswrd_post(request):
    curnt_pswrd=request.POST['currentpswrd']
    new_pswrd=request.POST['newpswrd']
    confrm_pswrd=request.POST['confirmpswrd']
    return HttpResponse("<script>alert('Successfully Changed Password')</script>")

def shop_login(request):
    return render(request,"SHOP/login.html")



def shop_home(request):
    return render(request,"SHOP/shop_index.html")


def shop_signup(request):
    return render(request,"SHOP/signup_index.html")

def signup_post(request):
    owner=request.POST['owner']
    shopname=request.POST['name']
    city=request.POST['city']
    district=request.POST['DISTRICT']
    pinn=request.POST['pin']
    phone=request.POST['ph']
    email=request.POST['email']
    web=request.POST['web']
    pswrd=request.POST['pswrd']
    cpassword=request.POST['cpassword']
    image=request.FILES['fileField']
    fs=FileSystemStorage()
    from datetime import datetime
    s=datetime.now().strftime("%Y%m%d%H%M%S")+".jpg"
    # s = 'Photo1' + datetime.now().strftime("%H%M%S-%d%m%Y") + ".jpg"
    fn = fs.save(s, image)
    lobj = login()
    lobj.username = email
    lobj.password =pswrd
    lobj.type = 'shop'
    lobj.save()


    hobj = shop()
    hobj.owner=owner
    hobj.image = fs.url(s)
    hobj.LOGIN = lobj
    hobj.status = 'pending'
    hobj.shop_name = shopname
    hobj.city = city
    hobj.district=district
    hobj.pin=pinn
    hobj.phone=phone
    hobj.email=email
    hobj.shop_web=web
    hobj.save()

    return HttpResponse("<script>alert('Account Created');window.location='/myapp/login/'</script>")



def profile(request):
    res=shop.objects.get(LOGIN=request.session["lid"])
    return render(request,"SHOP/Shop_viewprofile.html",{'data':res})

def profile_edit(request,mid):
    res = shop.objects.get(pk=mid)
    return render(request, "SHOP/edit_profile.html", {'data': res})

def profile_edit_post(request):
    id=request.POST['id']
    sowner=request.POST['owner']
    shopname = request.POST['name']
    city = request.POST['city']
    sdistrict = request.POST['DISTRICT']
    pinn = request.POST['pin']
    phone = request.POST['ph']
    email = request.POST['email']
    fs = FileSystemStorage()
    if 'fileField' in request.FILES:
        image = request.FILES["fileField"]
        if image.name !="":
            from datetime import datetime
            s = datetime.now().strftime("%Y%m%d%H%M%S") + ".jpg"
            # s = 'Photo1' + datetime.now().strftime("%H%M%S-%d%m%Y") + ".jpg"
            fn = fs.save(s, image)
            path=fs.url(s)
            res = shop.objects.filter(pk=id).update(owner=sowner, shop_name=shopname, city=city, district=sdistrict,
                                                    pin=pinn, phone=phone, email=email, image=path)
            return redirect('/myapp/profile/')
        else:
            res = shop.objects.filter(pk=id).update(owner_name=sowner, shop_name=shopname, city=city, district=sdistrict,
                                                    pin=pinn, phone=phone, email=email)
            return redirect('/myapp/profile/')
    else:
        res = shop.objects.filter(pk=id).update(owner_name=sowner, shop_name=shopname, city=city, district=sdistrict,
                                                pin=pinn, phone=phone, email=email)
        return redirect('/myapp/profile/')









def instru_cat(request):
    return render(request,"SHOP/Add_instrucat.html")

def shop_addinstru_post(request):
    shop_add_instrucategory=request.POST['instrucatname']


    catobj=category()
    catobj.category_name=shop_add_instrucategory
    catobj.SHOP_id=shop.objects.get(LOGIN_id=request.session['lid']).id
    catobj.save()


    return HttpResponse("<script>alert('Success');window.location='/myapp/instru_cat/'</script>")

def shop_view_category(request):
    res=category.objects.all()
    return render(request,'SHOP/view_category.html',{'data':res})

def edit_category(request,id):
    res=category.objects.get(id=id)
    return render(request,"SHOP/edit_instrucat.html",{'data':res})

def edit_category_post(request):
    id=request.POST['id']
    name=request.POST['instrucatname']
    res=category.objects.filter(pk=id).update(category_name=name)
    return HttpResponse("<script>alert('Updated..');window.location='/myapp/shop_view_category/'</script>")



def add_instruments(request):
    res=category.objects.all()
    return render(request,"SHOP/add_instrument.html",{'data':res})




def shop_addinstrument_post(request):
    imageinstru = request.FILES['instruimage']
    print(imageinstru)
    fs  = FileSystemStorage()
    from datetime import datetime
    dt = datetime.now().strftime("%Y%m%d%H%M%S") +imageinstru.name
    fn = fs.save(dt, imageinstru)
    shop_instrumentname=request.POST['instru_name']
    shop_instru_description=request.POST['description']
    shop_prize=request.POST['prize']
    shop_category=request.POST['select']

    shobj=instrument()
    shobj.imageinstru = fs.url(dt)
    shobj.name=shop_instrumentname
    shobj. rent_amount=shop_prize
    shobj.desc=shop_instru_description
    shobj.CATEGORY_id=shop_category
    shobj.SHOP_id=shop.objects.get(LOGIN_id=request.session['lid']).id

    shobj.save()

    return HttpResponse("<script>alert('Success');window.location='/myapp/add_instruments/'</script>")

def shop_viewinstrument(request):
    res=instrument.objects.all()
    return render(request,"SHOP/view_instrument.html",{'data':res})

def edit_instrument(request,id):
    res=instrument.objects.get(id=id)
    res1=category.objects.all()
    return render(request,"SHOP/edit_instrument.html",{'data':res,'data2':res1})


def edit_instrument_post(request):
    id=request.POST['id']


    name=request.POST['instru_name']
    cat_name=request.POST['select']
    description=request.POST['description']
    rentamount=request.POST['prize']

    if 'instruimage' in request.FILES:
        imageinstru = request.FILES['instruimage']
        if imageinstru.name !="":
            fs = FileSystemStorage()
            from datetime import datetime
            dt = datetime.now().strftime("%Y%m%d%H%M%S") + imageinstru.name
            fn = fs.save(dt, imageinstru)
            path=fs.url(dt)
            res = instrument.objects.filter(pk=id).update(name=name, CATEGORY_id=cat_name, desc=description,rent_amount=rentamount,imageinstru=path)
            return HttpResponse("<script>alert('Updated..');window.location='/myapp/shop_viewinstrument/'</script>")
        else:
            res = instrument.objects.filter(pk=id).update(name=name, CATEGORY_id=cat_name, desc=description,
                                                          rent_amount=rentamount)
            return HttpResponse("<script>alert('Updated..');window.location='/myapp/shop_viewinstrument/'</script>")
    else:
        res = instrument.objects.filter(pk=id).update(name=name, CATEGORY_id=cat_name, desc=description,
                                                      rent_amount=rentamount)
        return HttpResponse("<script>alert('Updated..');window.location='/myapp/shop_viewinstrument/'</script>")








def add_material(request):
    return render(request,"SHOP/Add_materialcat.html")

def shop_materialcat_post(request):
    shop_materialcat=request.POST['cat_name']

    mcobj=category()
    mcobj.category_name=shop_materialcat
    mcobj.save()


    return HttpResponse("<script>alert('Success')</script>")


def add_materials(request):
    res=category.objects.all()
    return render(request,"SHOP/Add_materials.html",{"data":res})



def shop_material_post(request):
    image=request.FILES['imagemat']
    shop_category=request.POST['catname']
    shop_materialname = request.POST['name']
    shop_materialdesc = request.POST['descr']
    shop_materialprize = request.POST['prize']
    fs = FileSystemStorage()


    from datetime import datetime
    s = datetime.now().strftime("%Y%m%d%H%M%S") + ".jpg"
    # dt =datetime.now().strftime("%H%M%S-%d%m%Y") + ".jpg"

    fn = fs.save(s, image)



    matobj=material()

    matobj.image = fs.url(s)
    matobj.name=shop_materialname
    matobj.description=shop_materialdesc
    matobj.rent_amount=shop_materialprize
    matobj.CATEGORY_id=shop_category
    matobj.SHOP=shop.objects.get(LOGIN_id=request.session['lid'])

    matobj.save()

    return HttpResponse("<script>alert('Success');window.location='/myapp/add_materials/'</script>")

def edit_mat(request,id):
    res=material.objects.get(pk=id)
    res1=category.objects.all()
    return render(request,'SHOP/edit_materials.html',{"data":res,"data1":res1})

def edit_mat_post(request):
    id=request.POST['id']
    shop_category = request.POST['catname']
    shop_materialname = request.POST['name']
    shop_materialdesc = request.POST['descr']
    shop_materialprize = request.POST['prize']


    if 'imagemat' in request.FILES:
        imageinstru = request.FILES['imagemat']
        if imageinstru.name !="":
            fs = FileSystemStorage()
            from datetime import datetime
            dt = datetime.now().strftime("%Y%m%d%H%M%S") + imageinstru.name
            fn = fs.save(dt, imageinstru)
            path=fs.url(dt)
            res = material.objects.filter(pk=id).update(name=shop_materialname, CATEGORY_id=shop_category, description=shop_materialdesc,rent_amount=shop_materialprize,image=path)
            return HttpResponse("<script>alert('Updated..');window.location='/myapp/shop_view_material/'</script>")
        else:
            res = material.objects.filter(pk=id).update(name=shop_materialname, CATEGORY_id=shop_category, description=shop_materialdesc,
                                                          rent_amount=shop_materialprize)
            return HttpResponse("<script>alert('Updated..');window.location='/myapp/shop_view_material/'</script>")
    else:
        res = material.objects.filter(pk=id).update(name=shop_materialname, CATEGORY_id=shop_category, description=shop_materialdesc,
                                                      rent_amount=shop_materialprize)
        return HttpResponse("<script>alert('Updated..');window.location='/myapp/shop_view_material/'</script>")





def shop_view_material(request):
    res=material.objects.all()
    return render(request,'SHOP/view_material.html',{"data":res})




def shop_viewMatreust(request):
    res=requst.objects.filter(status="Pending")
    return render(request,"SHOP/View_matreq.html",{'data':res})

def shop_viewconfrmMatreust(request):
    res=requst.objects.filter(status="Accepted")
    return render(request,"SHOP/View_confirmreq.html",{'data':res})


def shop_viewrejctdMatreust(request):
    res=requst.objects.filter(status="Rejected")
    return render(request,"SHOP/View_rejctdreq.html",{'data':res})


def shop_viewInstrureust(request):
    res=requst.objects.filter(status="Pending")
    return render(request,"SHOP/View_instrureq.html",{'data':res})


def shop_viewconfrmInstrureust(request):
    res=requst.objects.filter(status="Accepted")
    return render(request,"SHOP/View_confrminstru.html",{'data':res})


def shop_viewrejctdInstrurqust(request):
    res=requst.objects.filter(status="Rejected")
    return render(request,"SHOP/View_rejctdinstrureq.html",{'data':res})



def add_return_entry(request):
    return render(request,"SHOP/Add_returnentry.html")

def returnentry_post(request):
    item_name = request.POST['name']
    issue_date = request.POST['issuedate']
    return_date = request.POST['date']
    amount = request.POST['amount']

    robj = return_entry()
    robj.SHOP_id = shop.objects.get(LOGIN_id=request.session['lid']).id
    robj.item_name = item_name
    robj.issue_date = issue_date
    robj.return_date = return_date
    robj.amount = amount
    robj.save()
    return HttpResponse("<script>alert('Success')</script>")



def view_instrupayment(request):
    # shobj = shop.objects.get(LOGIN_id=request.session['lid'])
    # inobj = instrument.objects.filter(SHOP_id=shobj.id)
    # print(inobj)
    # robj = instrument_request.objects.get(INSTRUMENT_id=inobj.id)
    res=instru_payment.objects.all()
    # res='rowquery(SELECT * FROM `myapp_instru_payment` INNER JOIN `myapp_instrument_request` ON `myapp_instru_payment`.`REQUEST_id`=`myapp_instrument_request`.`id` INNER JOIN `myapp_instrument` ON `myapp_instrument_request`.`INSTRUMENT_id`=`myapp_instrument`.`id` )'

    return render(request,"SHOP/View_payment.html",{'data':res})


def view_instrupayment_post(request):
    fdate=request.POST['Fdate']
    tdate=request.POST['Tdate']
    res=instru_payment.objects.filter(date__range=[fdate,tdate])

def view_matpayment(request):
    res=material_payment.objects.all()
    # res = 'rowquery(SELECT * FROM `myapp_material_payment` INNER JOIN `myapp_material_request` ON `myapp_matrial_payment`.`REQUEST_id`=`myapp_material_request`.`id` INNER JOIN `myapp_material` ON `myapp_material_request`.`MATERIAL_id`=`myapp_material`.`id` )'
    return render(request, "SHOP/view_matpayment.html", {'data': res})


def view_matpayment_post(request):
    fdate = request.POST['Fdate']
    tdate = request.POST['Tdate']
    res = material_payment.objects.filter(date__range=[fdate, tdate])


def shop_review(request):
    res=shopreview.objects.all()
    return render(request,"SHOP/view_reviews.html",{'data':res})


def complaints(request):
    return render(request, "SHOP/Sent_complint.html")
def shop_complaint_post(request):
    shop_cmplint=request.POST['cmplint']

    cmobj=complaint()
    cmobj.compalint=shop_cmplint
    cmobj.LOGIN = login.objects.get(id=request.session['lid'])
    cmobj.save()


    return HttpResponse("<script>alert('Success')</script>")

def shop_view_reply(request):
    res=complaint.objects.all()
    return render(request,"SHOP/View_reply.html",{'data':res})


def gallery(request):
    return render(request,"SHOP/Shop_gallery.html")
def shop_gallery_post(request):
    shop_image1=request.FILES['image1shop']
    shop_image2=request.FILES['image2shop']
    shop_image3=request.FILES['image3shop']
    fs = FileSystemStorage()
    fs2 = FileSystemStorage()
    fs3 = FileSystemStorage()

    from datetime import datetime
    dt = 'Photo1' + datetime.now().strftime("%H%M%S-%d%m%Y") + ".jpg"
    dt2 = 'Photo2' + datetime.now().strftime("%H%M%S-%d%m%Y") + ".jpg"
    dt3 = 'Photo3' + datetime.now().strftime("%H%M%S-%d%m%Y") + ".jpg"

    fn = fs.save(dt, shop_image1)
    fn1 = fs2.save(dt2, shop_image2)
    fn2 = fs3.save(dt3, shop_image3)

    sobj=shop_gallery()

    sobj.image1=fs.url(dt)
    sobj.image2=fs2.url(dt2)
    sobj.image3=fs3.url(dt3)
    sobj.SHOP_id = shop.objects.get(LOGIN_id=request.session['lid']).id
    sobj.save()

    return HttpResponse("<script>alert('Success')</script>")



# def shop_view_gallery(request):
#     res = shop_gallery.objects.filter(SHOP_id=shop.objects.get(LOGIN_id=request.session['lid']).id)
#     return render(request, "SHOP/View_gallery.html", {'data': res})

def shop_view_gallery(request):
    a=[]
    res=shop_gallery.objects.filter(SHOP_id=shop.objects.get(LOGIN_id=request.session['lid']).id)
    for i in res:
        a.append({'im':i.image1})
    print(a[0])
    return render(request,"SHOP/Shop_view_gallery.html",{'data':res,'a':a})

def shop_view_gallery_more(request):
  res=shop_gallery.objects.filter(SHOP_id=shop.objects.get(LOGIN_id=request.session['lid']).id)
  print(res)
  return render(request,"SHOP/shop_view_more.html",{'data':res})


def shop_delete_gallery(request, id):
    res = shop_gallery.objects.filter(id=id).delete()
    return HttpResponse('<script>alert("Deleted");window.location="/myapp/shop_view_gallery_more/"</script>')





#=========Android================

# def user_login(request):
#     return render(request,"USER/user_login.html")

def and_user_login(request):
    username=request.POST['username']
    password=request.POST['password']
    if login.objects.filter(username=username,password=password).exists():
        lg=login.objects.get(username=username,password=password,type='user')
        return JsonResponse({'status':'ok','lid':lg.id,'type':lg.type})
    else:
        return JsonResponse({"status":"no"})


def and_usersignup_post(request):
    uname=request.POST['name']
    uemail=request.POST['email']
    phone=request.POST['phone']
    address=request.POST['address']
    city=request.POST['city']
    district=request.POST['district']
    pin=request.POST['pin']
    password=request.POST['password']
    confirm=request.POST['confirm']

    lobj=login()
    lobj.username=uemail
    lobj.password=confirm
    lobj.type='user'
    lobj.save()

    uobj=user()
    uobj.name=uname
    uobj.email=uemail
    uobj.phone=phone
    uobj.address=address
    uobj.city=city
    uobj.district=district
    uobj.pin=pin
    uobj.LOGIN=lobj
    uobj.save()
    return JsonResponse({"status":"ok"})







def and_user_profile(request):
    lid=request.POST['lid']
    print(lid)
    i=user.objects.get(LOGIN=lid)
    # l=[]
    # for i in res:
    #     l.append({'uid':i.id,'name':i.name,'email':i.email,'address':i.address,'city':i.city,'district':i.district,'pin':i.pin,'phone':i.phone})

    return JsonResponse({'status':'ok','uid':i.id,'name':i.name,'email':i.email,'address':i.address,'city':i.city,'district':i.district,'pin':i.pin,'phone':i.phone})


def and_edit_profile(request):
    lid=request.POST['lid']
    uname = request.POST['name']
    uemail = request.POST['email']
    phone = request.POST['phone']
    address = request.POST['address']
    city = request.POST['city']
    district = request.POST['district']
    pin = request.POST['pin']

    res = user.objects.filter(LOGIN_id=lid).update(name=uname, email=uemail, phone=phone,
                                                  address=address, city=city,district=district,pin=pin)
    return JsonResponse({'status':"ok"})


# def user_chnge_pswrd(request):
#     return render(request,"USER/user_chngepswrd.html")

# def and_user_changepswrd_post(request):
#     user_curntpswrd=request.POST['curntpswrd']
#     user_newpswrd=request.POST['newpswrd']
#     lid=request.POST['lid']
#     lobj=login.objects.filter(id=lid,password=user_curntpswrd)
#     if lobj.exists():
#         lobjj=login.objects.get(id=lid,password=user_curntpswrd)
#         lobjj.password=user_newpswrd
#         lobjj.save()
#         return HttpResponse(status="ok")
#
#     else:
#
#         return HttpResponse(status="no")

def and_view_insti(request):
    res=institute.objects.filter(status="approved")
    l=[]
    for i in res:
        l.append({'i_id':i.id,'manager':i.manager,'name':i.name,'email':i.email,'city':i.city,'district':i.district,'pin':i.pin,'phone':i.phone,'image':i.image,'LOGIN_id':i.LOGIN_id,'website':i.website})
    return JsonResponse({'status':'ok','data':l})

def and_view_insti_more(request):
    lid=request.POST['iid']
    res=institute.objects.get(id=lid)
    l=({'i_id':res.id,'manager':res.manager,'name':res.name,'email':res.email,'city':res.city,'district':res.district,'pin':res.pin,'phone':res.phone,'image':res.image,'LOGIN_id':res.LOGIN_id,'website':res.website})
    return JsonResponse({'status':'ok','data':l})

def and_view_insti_gallery(request):
    i_lid=request.POST['i_lid']
    res=insti_gallery.objects.filter(INSTITUTE_id=i_lid)
    l=[]
    for i in res:
        l.append({'g_id': i.id, 'im1': i.image1, 'im2': i.image2, 'im3': i.image3,'i_id':i.INSTITUTE_id})
    return JsonResponse({'status': 'ok', 'data': l})


def and_view_shop_gallery(request):
    i_lid=request.POST['i_lid']
    print(i_lid)
    res=shop_gallery.objects.filter(SHOP_id=i_lid)
    l=[]
    for i in res:
        l.append({'g_id': i.id, 'im1': i.image1, 'im2': i.image2, 'im3': i.image3,'i_id':i.SHOP_id})
    print(l)
    return JsonResponse({'status': 'ok', 'data': l})




def and_insti_request(request):
    lid=request.POST['lid']
    i_lid=request.POST['i_lid']
    robj=course_requst()
    from datetime import datetime
    date=datetime.now().strftime("%Y-%m-%d")
    robj.date=date
    robj.USER_id=user.objects.get(LOGIN_id=lid).id
    robj.INSTITUTE_id=i_lid
    robj.status='pending'
    robj.save()
    return JsonResponse({'status':"ok"})

def and_view_request_status(request):
    lid=request.POST['lid']
    print(lid)
    res=course_requst.objects.filter(USER_id=lid)
    print(res)
    l=[]
    for i in res:
        l.append({'r_id':i.id,'date':i.date,'status':i.status,'insti_name':i.INSTITUTION.id,'lid':i.USER_id})
    return JsonResponse({'status':'ok','data':l})

def and_sent_insti_review(request):

    lid=request.POST['lid']
    print(lid)
    i_id=request.POST['i_id']
    review=request.POST['review']
    print(i_id)
    from datetime import datetime
    date=datetime.now().strftime("%Y-%m-%d")
    rvobj=instireview()
    rvobj.date=date
    rvobj.review=review
    rvobj.USER_id=user.objects.get(LOGIN_id=lid).id
    rvobj.INSTITUTE_id=i_id
    rvobj.save()
    return JsonResponse({'status':"ok"})

def and_view_insti_review(request):
    # lid=request.POST['lid']
    i_id=request.POST['i_id']
    res = instireview.objects.filter(INSTITUTE_id=i_id)
    # res = instireview.objects.filter(USER_id=user.objects.get(LOGIN_id=lid), INSTITUTE_id=i_id)
    l=[]
    for i in res:
        l.append({'r_id': i.id, 'review': i.review, 'LOGIN_id': i.USER_id, 'insti_name': i.INSTITUTE_id, 'date': i.date,'uname':i.USER.name})
    print(l)
    return JsonResponse({'status': 'ok', 'data': l})



def and_view_course(request):
    i_lid=request.POST['i_lid']
    res=course.objects.filter(INSTITUTE_id = i_lid)
    l = []
    print(l)
    print(res)
    for i in res:
        m = {'c_id': i.id, 'cname': i.course_name, 'cfee': i.course_fee, 'cd': i.course_duration, 'cld': i.class_duration,'seat':i.available_seats,'iid':i.INSTITUTE_id}
        l.append(m)
    return JsonResponse({'status': 'ok', 'data': l})





def and_view_batch(request):
    cid=request.POST['cid']
    res=batch.objects.filter(COURSE_id=cid)
    l=[]
    for i in res:
        l.append({'bid':i.id,'bname':i.batch_name,'seat':i.no_of_seats,'date':i.date,'t1':i.time1,'t2':i.time2,'cid':i.COURSE_id,'iid':i.INSTITUTE_id,'cname':i.COURSE.course_name})
    return JsonResponse({'status': 'ok', 'data': l})



def and_view_shop(request):
    # res = shop.objects.filter(status="approved")
    res=category.objects.filter(SHOP__status="approved")
    l = []
    for i in res:
        l.append({'s_id': i.SHOP.id, 'name': i.SHOP.shop_name, 'manager': i.SHOP.owner, 'city': i.SHOP.city,
                  'district': i.SHOP.district, 'pin': i.SHOP.pin, 'phone': i.SHOP.phone, 'email': i.SHOP.email, 'image': i.SHOP.image, 'status':i.SHOP.status,'LOGIN_id': i.SHOP.LOGIN_id,
                  'website': i.SHOP.shop_web,'category':i.category_name})
    return JsonResponse({'status': 'ok', 'data': l})


def and_view_shop_more(request):
    lid = request.POST['sid']
    print(lid)
    res = shop.objects.get(LOGIN_id=lid)
    l = ({'s_id': res.id, 'manager': res.owner, 'name': res.shop_name, 'city': res.city,
          'district': res.district, 'pin': res.pin, 'phone': res.phone, 'email': res.email, 'image': res.image, 'LOGIN_id': res.LOGIN_id,
          'website': res.shop_web})
    return JsonResponse({'status': 'ok', 'data': l})



def and_sent_shop_review(request):
    lid = request.POST['lid']
    s_id = request.POST['sid']
    print(s_id,"fhfhfhf")
    review = request.POST['review']
    from datetime import datetime
    date = datetime.now().strftime("%Y-%m-%d")
    rvobj = shopreview()
    rvobj.date = date
    rvobj.review = review
    rvobj.USER_id= user.objects.get(LOGIN_id=lid).id
    rvobj.SHOP=shop.objects.get(LOGIN_id=s_id)
    rvobj.save()
    return JsonResponse({'status': "ok"})

def and_view_shop_review(request):
    lid=request.POST['lid']
    s_id=request.POST['sid']
    print(s_id,"hhhhh")
    res = shopreview.objects.filter(SHOP__LOGIN_id=s_id)
    l=[]
    for i in res:
        l.append({'r_id': i.id, 'date': i.date, 'review': i.review, 'lid': i.USER_id, 'insti_name': i.SHOP.shop_name})
    return JsonResponse({'status': 'ok', 'data': l})

def and_view_category(request):
    lid=request.POST['sid']
    print(lid)
    res=category.objects.filter(SHOP__LOGIN_id=lid)
    l=[]
    for i in res:
        l.append({'c_id':i.id,'cname':i.category_name})
    return JsonResponse({'status': 'ok', 'data': l})


def and_view_instrument(request):
    cid=request.POST['cid']
    res=instrument.objects.filter(CATEGORY_id=cid)
    slid=request.POST['sid']
    l=[]
    for i in res:
        l.append({'i_id': i.id, 'name': i.name, 'rent': i.rent_amount, 'desc': i.desc, 'image': i.imageinstru,'slid':i.SHOP_id,'cid':i.CATEGORY_id})
    print(l)
    return JsonResponse({'status': 'ok', 'data': l})

def and_view_material(request):
    cid=request.POST['cid']
    slid=request.POST['sid']
    print(slid)
    print(cid)
    res=material.objects.filter(CATEGORY_id=cid,SHOP__LOGIN_id=slid)
    print(res)
    l=[]
    for i in res:
        l.append({'i_id': i.id, 'name': i.name, 'rent': i.rent_amount, 'desc': i.description, 'image': i.image,'slid':i.SHOP_id,'cid':i.CATEGORY_id})
    return JsonResponse({'status': 'ok', 'data': l})

def and_send_material_request(request):
    mid = request.POST['mid']
    lid = request.POST['lid']
    amnt = request.POST['amt']
    f_date = request.POST['fdate']
    t_date = request.POST['tdate']
    from datetime import datetime
    start_date = datetime.strptime(f_date, "%d/%m/%Y")
    end_date = datetime.strptime(t_date, "%d/%m/%Y")

    r_amount = int(abs((end_date - start_date).days)) * int(amnt)

    mobj=material_request()
    from datetime import datetime
    date = datetime.now().strftime("%Y-%m-%d")
    mobj.date = date
    mobj.USER_id = user.objects.get(LOGIN_id=lid).id
    mobj.MATERIAL_id =mid
    mobj.status = 'pending'
    mobj.from_date=f_date
    mobj.to_date=t_date
    mobj.total_amount=r_amount
    mobj.save()
    return JsonResponse({'status': "ok"})

def and_send_instrument_request(request):
    mid=request.POST['mid']
    lid=request.POST['lid']
    amnt=request.POST['amt']
    f_date=request.POST['fdate']
    t_date=request.POST['tdate']
    from datetime import datetime
    start_date = datetime.strptime(f_date, "%d/%m/%Y")
    end_date = datetime.strptime(t_date, "%d/%m/%Y")

    r_amount=int(abs((end_date - start_date).days)) * int(amnt)

    iobj=instrument_request()
    from datetime import datetime
    date = datetime.now().strftime("%Y-%m-%d")
    iobj.date = date
    iobj.USER_id = user.objects.get(LOGIN_id=lid).id
    iobj.INSTRUMENT_id=mid
    iobj.from_date=f_date
    iobj.to_date=t_date
    iobj.total_amount=r_amount
    iobj.status = 'pending'
    iobj.save()
    return JsonResponse({'status': "ok"})

def and_view_material_request_status(request):
    lid=request.POST['lid']
    # mid=request.POST['mid']
    uu=user.objects.get(LOGIN_id=lid)
    res=material_request.objects.filter(USER=uu)
    print(res)
    l=[]
    for i in res:
        l.append({'r_id':i.id,'date':i.date,'status':i.status,'lid':i.USER_id,'insti_name':i.MATERIAL.name,'rent':i.MATERIAL.rent_amount,'fdate':i.from_date,'tdate':i.to_date,'tamnt':i.total_amount})
    print(l)
    return JsonResponse({'status':'ok','data':l})

def and_view_instrument_request_status(request):
    lid=request.POST['lid']
    # iid=request.POST['iid']
    print(lid)
    u=user.objects.get(LOGIN_id=lid).id
    res=instrument_request.objects.filter(USER_id=u)
    l=[]
    for i in res:
       l.append({'r_id':i.id,'date':i.date,'status':i.status,'lid':i.USER_id,'insti_name':i.INSTRUMENT.name,'rent':i.INSTRUMENT.rent_amount,'fdate':i.from_date,'tdate':i.to_date,'amont':i.total_amount})
    print(l)
    return JsonResponse({'status':'ok','data':l})


def and_payment(request):
    lid=request.POST['lid']
    rid=request.POST['req']
    print(rid)
    # ifsc=request.POST['ifsc']
    accnthname=request.POST['hname']
    cvv=request.POST['cvvv']
    acnt=request.POST['accnt']
    amnt=request.POST['amt']
    edate=request.POST['edate']
    ptype=request.POST['ptype']
    print(request.POST)
    aa=bank.objects.filter(holder=accnthname, cnumber=acnt, cvv=cvv,exp_date=edate)
    print(aa)
    if aa.exists():
        bb = bank.objects.get(holder=accnthname, cnumber=acnt, cvv=cvv,exp_date=edate)
        if(int(bb.balance)>=int(amnt)):
            if ptype=='instr':
                pobj=instru_payment()
                pobj.amount=amnt
                pobj.payment_type=acnt
                pobj.REQUEST_id=instrument_request.objects.get(pk=rid).id
                pobj.date=datetime.datetime.now()
                pobj.exp_date=edate
                pobj.USER=user.objects.get(LOGIN_id=lid).id
                pobj.save()
                bb.balance=int(bb.balance)-int(amnt)
                instrument_request.objects.filter(pk=rid).update(status="paid")
                bb.save()
                return JsonResponse({"status":"ok"})

            else:
                mpobj = material_payment()
                mpobj.amount = amnt
                mpobj.payment_type = acnt
                mpobj.REQUEST_id=material_request.objects.get(pk=rid).id
                mpobj.exp_date=edate
                mpobj.date = datetime.datetime.now()
                mpobj.USER = user.objects.get(LOGIN_id=lid)
                mpobj.save()
                material_request.objects.filter(pk=rid).update(status='paid')
                bb.balance=int(bb.balance)-int(amnt)
                bb.save()
                return JsonResponse({"status":"ok"})
        else:
            return JsonResponse({"status": "no"})


    else:
        return JsonResponse({"status":"no"})


#     return render(request,"USER/View_batch.html")
# def and_sent_req(request):
#     return HttpResponse(status="ok")


def and_send_apprating(request):
    lid=request.POST['lid']
    bar=request.POST['rate']
    from datetime import datetime
    date=datetime.now().strftime("%Y-%m-%d")
    aobj=app_rating()
    aobj.rating=bar
    aobj.date=date
    aobj.USER_id=user.objects.get(LOGIN_id=lid).id
    aobj.save()
    return JsonResponse({'status':'ok'})

def and_view_apprating(request):
    lid=request.POST['lid']
    u = user.objects.get(LOGIN_id=lid).id
    res = app_rating.objects.filter(USER_id=u)
    l = []
    for i in res:
        l.append({'a_id':i.id,'rate':i.rating,'date':i.date,'u_lid':i.USER_id})
    return JsonResponse({'status':'ok','data':l})



#     return render(request,"USER/View_batch.html")
# def and_sent_req(request):
#     return HttpResponse(status="ok")




    # return render(request,"USER/sent_requst.html")
# def user_sentrequst_post(request):
#     user_reqname=request.POST['name']
#     user_adrss=request.POST['adrss']
#     user_city=request.POST['city']
#     user_dist=request.POST['dist']
#     user_phone=request.POST['phone']
#     user_coursename=request.POST['course']
#     return HttpResponse("Success")

# def and_view_schedule(request):
#     res=schedule.objects.all()
#     return HttpResponse(status="ok")
#     return render(request,"USER/view_schedules.html")
# def view_insti_gallery(request):
#     return render(request,"USER/View_institutegallery.html")
# def sent_complaint(request):
#     return render(request,"USER/user_sentcomplaint.html")
# def and_user_sentcomplint_post(request):
#     user_sentcmplint=request.POST['cmplint']
#     from datetime import datetime
#     date=datetime.now().strftime("%Y-%m-%d")
#     lid=request.POST['lid']
#
#     ucomobj=std_complaint()
#     ucomobj.compalint=user_sentcmplint
#     ucomobj.date=date
#     ucomobj.reply='pending'
#     ucomobj.STUDENT=student.objects.get(LOGIN_ID=lid)
#     ucomobj.save()
#
#     return JsonResponse(status="ok")

# def and_view_shop(request):
#     res=shop.objects.all(status="approved")
#     return JsonResponse(status="ok")
    # return render(request,"USER/view_shop.html")



# def and_view_instru(request):
#     res=instrument.objects.all()
#     return HttpResponse(status="ok")
    # return render(request,"USER/View_instrumentcat.html")


# def instrumentt(request):
#     return render(request,"USER/view_available_instru.html")


# def sent_requst(request):
#     return render(request,"USER/sent_requstinstru.html")
# def user_instrurequest_post(request):
#     user_name2 = request.POST['name']
#     user_adrs2 = request.POST['adrss']
#     user_city2 = request.POST['city']
#     user_mobile2 = request.POST['phone']
#     user_sentinstrureq = request.POST['requestt']
#     return HttpResponse("Success")


# def sent_payment(request):
#     return render(request,"USER/sent_payment.html")
# def and_user_payment_post(request):
#     user_sentpayment=request.POST['name']
#     user_amount=request.POST['amnt']
#
#     return HttpResponse(status="ok")

# def and_view_payment(request):
#     return JsonResponse(status="ok")
    # return render(request,"USER/View_payment.html")
# def view_mat(request):
#     return render(request,"USER/View_materialcat.html")
# def and_view_material(request):
#     return HttpResponse(status="ok")

#     return render(request,"USER/View_material.html")
# def sent_matreq(request):
#     return render(request,"USER/sent_reqstmat.html")
# def user_sentrqst_post(request):
#     user_name3 = request.POST['name']
#     user_adrs3 = request.POST['adrss']
#     user_city3 = request.POST['city']
#     user_mobile3 = request.POST['phone']
#     user_sentmaterialreq = request.POST['materialname']
#     return HttpResponse("Success")

# def and_view_shop_gallery(request):

    # return render(request,"USER/View_shopgallery.html")
# def view_review(request):
#     return render(request,"USER/sent_viewreviews.html")





# def user_review_post(request):
#     user_nme=request.POST['name']
#     user_review=request.POST['reviews']
#     return JsonResponse(status="ok")




