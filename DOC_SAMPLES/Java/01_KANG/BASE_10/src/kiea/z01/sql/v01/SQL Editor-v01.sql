----------------------------------------------------------------------
-- ANAL_CSV 확인
select
	job_id
	, prog_nm
	, list_cnt
	, r_msec
	, csv_status
	, log_message
from
	anal_csv
where 1=1
	--and job_id = 'AR010141204A3000'
	--and job_id >= 'AR010141204A3000'
	and job_id like 'AR010141204%'
	and prog_nm like 'A01%'
	and list_cnt > 0
order by
	job_id desc
	, prog_nm
;


----------------------------------------------------------------------
-- ANAL_CSV 확인 : PROG_NM = A24%
select
	job_id
	, prog_nm
	, list_cnt
	, r_msec
	, csv_status
	, log_message
from
	anal_csv
where 1=1
	and job_id like 'AR010141204%'
	and job_id >= 'AR010141204A3140'
	and prog_nm like 'A24%'
	and list_cnt > 0
order by
	job_id desc
	, prog_nm
;

----------------------------------------------------------------------
-- 분석 진행중인 JOBID 
select * from anal_jobid where 1=1 and job_id like 'AR___141204%' order by job_id desc;

----------------------------------------------------------------------
-- 분석 진행중인 JOBID 
select * from anal_jobid where status not in ('FINISH') and job_id like 'AR___141204%' order by job_id desc;


