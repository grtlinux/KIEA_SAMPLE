#!/bin/bash

dirlist=`ls ./ | grep -v .sh `

for dir in ${dirlist}; do
    echo ${dir}
    filelist=`ls ${dir}/*.gz`

    for file in ${filelist}; do
        ## decompress gzip
        echo ${file}
        echo "gzip -d ${file}"
        gzip -d ${file}

        ## iconv file type utf-16 utf-8 
        orgfilenm=${file%.gz}
        echo "iconv -f UTF-16 -t UTF-8 ${orgfilenm} -o ${orgfilenm}.utf8" 
        iconv -f UTF-16 -t UTF-8 ${orgfilenm} -o ${orgfilenm}.utf8

        ## file count 
        utf8FileCount=`cat ${orgfilenm}.utf8 |  wc -l`
        utf16FileCount=`cat ${orgfilenm} | wc -l`

        if [ ${utf8filecount} -eq ${utf16filecount} ]; then
            tmpUtf8FileCount=`expr ${utf8FileCount} - 1`

            ## column row delete and file recount
            cat ${orgfilenm}.utf8 | egrep -v "^Type" >> ${orgfilenm}.utf8.tmp
            mv ${orgfilenm}.utf8.tmp ${orgfilenm}.utf8
            nocolumnCount=`cat ${orgfilenm}.utf8 | wc -l`

            ## diff filecount
            if [ ${tmpUtf8FileCount} -ne ${nocolumnCount} ]; then
                echo "${orgfilenm}.utf8 file error "
                exit 1
            fi

            ## compress gzip
            echo "gzip -q ${orgfilenm}.utf8"
            gzip -q ${orgfilenm}.utf8
        else
            echo "[ERROR] ${orgfilenm} and ${orgfilenm}.utf8 count different"
            exit 1
        fi

    done

done
