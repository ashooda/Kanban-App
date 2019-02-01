Create table public.customer
(
  customer_id Serial primary KEY  not null,
  customer_first_name   varchar(20) not null,
  customer_last_name    varchar(20) not null,
  customer_email        varchar(20),
  customer_phone_number varchar(20) not null


);


Create table public.jobstatus
(
  job_status_id Serial primary KEY  not null,
  job_status   varchar(20) not null

);


Create table public.component
(
  component_id Serial primary KEY  not null,
  component_name   varchar(20) not null,
  component_price  float

);

Create table public.employee
(
  employee_id Serial primary KEY  not null,
  employee_first_name   varchar(50) not null,
  employee_last_name    varchar(50) not null,
  employee_email        varchar(50),
  employee_phone_number varchar(50) not null


);

Create table public.job
(
  job_id Serial primary KEY  not null,
  customer_id   integer references customer(customer_id) not null,
  job_status_id integer  references jobstatus(job_status_id) not null,
  product_name  varchar(20) not null,
  total_price  float ,
  profit  float
);

Create table public.jobcomponent
(
  job_component_id Serial primary KEY  not null,
  job_id   integer references job(job_id) not null,
  component_id integer  references component(component_id) not null,
  quantity integer
);