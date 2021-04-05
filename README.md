# install docker

### on ubuntu

install docker

```shell
sudo apt update -y
sudo apt install -y docker.io
```

verify installation

```shell
docker -v
=>Docker version 20.10.2, build 20.10.2-0ubuntu2
```

### window or mac

please refer to installation [manual](https://docs.docker.com/get-docker/) 

# setup server 

### download source code

```shell
git clone "https://github.com/centralhardware/test"
```

### build docker image

```shell
sudo docker build -t  captcha_server .
```

### run
```shell
sudo docker run -d -p 8080:8080    \
  -e CAPTCHA_STRING_LENGTH=5       \
  -e CAPTCHA_IS_USE_LETTERS=true   \ 
  -e CAPTCHA_IS_USE_NUMBERS=false  \ 
  captcha_server
```


go to the server [endpoint](http://localhost:8080/get-captcha)

# configuration 

| environment variable    | description                                         | default value  |
| ----------------------- |:---------------------------------------------------:| --------------:|
| CAPTCHA_STRING_LENGTH   | captcha text length                                 | 7              |
| CAPTCHA_IS_USE_LETTERS  | will be used alphabetic characters                  | true           |
| CAPTCHA_IS_USE_NUMBERS  | will numbers be used                                | true           |