# Generated by Django 4.1.3 on 2023-05-14 04:12

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('myapp', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='bank',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('account', models.CharField(max_length=50)),
                ('cvv', models.CharField(max_length=50)),
                ('ifsc', models.CharField(max_length=50)),
                ('holder', models.CharField(max_length=50)),
                ('balance', models.CharField(max_length=50)),
            ],
        ),
        migrations.RemoveField(
            model_name='coursebooking',
            name='PAYMENT',
        ),
        migrations.RemoveField(
            model_name='rent',
            name='PAYMENT',
        ),
        migrations.CreateModel(
            name='material_payment',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('date', models.CharField(max_length=10)),
                ('amount', models.CharField(max_length=20)),
                ('payment_type', models.CharField(max_length=20)),
                ('REQUEST', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='myapp.material_request')),
                ('USER', models.ForeignKey(default=1, on_delete=django.db.models.deletion.CASCADE, to='myapp.user')),
            ],
        ),
        migrations.CreateModel(
            name='instru_payment',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('date', models.CharField(max_length=10)),
                ('amount', models.CharField(max_length=20)),
                ('payment_type', models.CharField(max_length=20)),
                ('REQUEST', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='myapp.instrument_request')),
                ('USER', models.ForeignKey(default=1, on_delete=django.db.models.deletion.CASCADE, to='myapp.user')),
            ],
        ),
        migrations.CreateModel(
            name='app_rating',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('rating', models.CharField(max_length=10)),
                ('date', models.CharField(max_length=15)),
                ('USER', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='myapp.user')),
            ],
        ),
    ]