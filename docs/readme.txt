template engine
timing task

passport
dashboard


https://fancy.com/hongquanli
infinite
thymeleaf

System.setProperty("spring.profiles.active", "production");

MQTT

Kotlin in Action
Kotlin for Android Developers
https://light.hs.net/portal/not-support.html


lightart/github

encyclopedia
Scalable
Resources

快速清空文件内容：
　　$ : > filename #其中的 : 是一个占位符, 不产生任何输出.
　　$ > filename
　　$ echo '' > filename
　　$ echo /dev/null > filename
　　$ echo > filename
　　$ cat /dev/null > filename


泛化、继承、实现、依赖、关联、聚合、组合
单例 多例 静态 线程安全

Behavior
Event
Action
Command

Service

Request
Response
Context
Data

Content-Type: MIME
.xml text/xml
.json text/json

SearchCriteria

attachment
encoding
version

Backup
Data
Temp
Video
Attachment
Config
Image
Favorite
Docs

prototype

singleton

Resources
Representation
State Transfer

ConfigurableBeanFactory
AbstractBeanFactory

Search criteria/command


support

// Request
{
    access_key:'',
    source_address:'C0-3F-D5-E5-20-51',
    mac:'',
    compress_algorithm:''
    signature_algorithm:''
    encrypt_algorithm:'',
    signature:'',
    version:'1.0',
    service:'Behavior, Event, Action, Command',
    content_type:'text/json',
    encoding:'UTF-8',
    timestamp:'1445950906202',
    description:'',
    arguments:{
        'props':{

        }
    },
    data:{
        offset:'',
        limit:'',
        content:''
    }
}

// Response
{
    path:'',
    service:'',
    status:'',
    message:'',
    error:'',
    timestamp:'1445950906202',
    version:'1.0',
    content_type:'text/json',
    encoding:'UTF-8',
    description:'',
    arguments:{
        'props':{

        }
    },
    data:{
        page_number:'Behavior, Event, Action, Command',
        page_size:20,
        total_elements:'',
        total_pages:'',
        is_first:true,
        is_last:false,
        has_next:'',
        has_previous:'',
        content:{
            // ......
        }
    }
}


target

{
    “userKey”: “975bf42bdf837a1e43508a9bf6340420”,
    “sourceAddress”: “120.2.6.5”,
    “mac”: “C0-3F-D5-E5-20-51”,
    “compressAlgorithm”: “gzip”,
    “signatureAlgorithm”: “”,
    “encryptAlgorithm”: “”,
    “signature”: “”,
    “encoding”: “UTF-8”,
    “version”: “1.0”,
    “timestamp”: “1445950906202”,
    “description”: “”,
    “arguments”: {
        “props”: {
            “key”: “value”,
            ……
        }
    }
}

{
    “responseCode”: “”,
    “responseMessage”: “”,
    “compressAlgorithm”: “gzip”,
    “signatureAlgorithm”: “”,
    “encryptAlgorithm”: “”,
    “serviceResponseCode”: “”,
    “serviceResponseMessage”: “”,
    “exceptionDetail”: “”,
    “encoding”: “UTF-8”,
    “version”: “1.0”,
    “timestamp”: “1445950906202”,
    “description”: “”,
    “arguments”: {
        “props”: {
            “key”: “value”,
            ……
        }
    }
}
