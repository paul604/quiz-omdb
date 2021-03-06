var adress = "https://lp-miar-groupe01-cloned-paul604.c9users.io/quizz-movie-1.0.0";

var vue = new Vue({
  el: '#vue',
  data: {
    body: "loginTemp",
    precedentPage: "loginTemp",
    score: 0,
    totalScore: 0,
    totalQuestion: 0,
    login: "",
    password: "",
    token: "",
    questionPicture: "",
    question: "",
    answer: "",
    imageError: "",
    textError: "",
  },
  computed: {
    successRate: function() {
      return (this.totalQuestion === 0) ? 0 : ((this.totalScore/this.totalQuestion)*100);
    }
  },
  methods: {
    toStats: function() {
      this.httpRequestAsync("GET", adress+"/answers?token="+this.token, "", function(json){
        var jsonObject = JSON.parse(json);
        this.totalQuestion = parseInt(jsonObject.answers);
        this.httpRequestAsync("GET", adress+"/goodanswers?token="+this.token, "", function(json){
          var jsonObject = JSON.parse(json);
          this.totalScore = parseInt(jsonObject.goodanswers);
          this.body = "statsTemp";
        }.bind(this));
      }.bind(this));
    },
    loginToQuiz: function() {
      this.connection(function(json) {
        this.token = JSON.parse(json).token;
        this.toQuiz();
      }.bind(this));
    },
    loginToRegister: function() {
      this.body = "registerTemp";
    },
    toLogin: function() {
      this.resetConnection();
      this.body = "loginTemp";
    },
    registerToQuiz: function() {
      this.register(function(json) {
        this.body = "loginTemp";
      }.bind(this));
    },
    toRegister: function() {
      this.disconnection();
      this.body = "registerTemp";
    },
    toQuiz: function() {
      this.getQuestion();
      this.body = "quizTemp";
    },
    toLegal: function() {
      if(this.body != "legalTemp") {
        this.precedentPage = this.body;
        this.body = "legalTemp";
      }
    },
    toError: function(statusCode, statusText) {
      if(this.body != "errorTemp") {
        this.precedentPage = this.body;
        this.imageError = "https://http.cat/"+statusCode;
        this.textError = statusText;
        this.body = "errorTemp";
      }
    },
    toPrecedent: function() {
      this.body = this.precedentPage
    },
    disconnection: function(){
      this.httpRequestAsync("POST", adress+"/disconnect?token="+this.token, "", function(json) {
        this.resetConnection();
        this.body = "loginTemp";
      }.bind(this));
    },
    resetConnection: function() {
      this.login = "";
      this.password = "";
      this.token = "";
      this.score = 0;
      this.totalScore = 0;
      this.totalQuestion = 0;
    },
    connection: function(callback) {
      var params = "{\"login\":\""+this.login+"\", \"password\":\""+this.password+"\"}";
      this.httpRequestAsync("POST", adress+"/login", params, callback);
    },
    register: function(callback) {
      var params = "{\"login\":\""+this.login+"\", \"password\":\""+this.password+"\"}";
      this.httpRequestAsync("POST", adress+"/register", params, callback);
    },
    answerQuestion: function() {
      var params = "";
      this.httpRequestAsync("POST", adress+"/response?token="+this.token, "{\"response\":\""+this.answer+"\"}", function(json) {
        var jsonObject = JSON.parse(json);
        if(jsonObject.result==="true") {
          this.score ++;
          this.totalScore ++;
        }
        this.getQuestion();
        this.answer = "";
        this.question = "";
        this.questionPicture = "";
        this.totalQuestion ++;
      }.bind(this));
    },
    getQuestion: function() {
      this.httpRequestAsync("GET", adress+"/question?token="+this.token, "", function(json) {
        var jsonObject = JSON.parse(json);
        this.question = "<b>Question: </b>"+jsonObject.question;
        this.questionPicture = jsonObject.poster;
      }.bind(this));
    },
    httpRequestAsync: function(type, theUrl, params, callback) {
      var xmlHttp = new XMLHttpRequest();
      xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4) {
          if(xmlHttp.status == 200) {
            callback(xmlHttp.responseText);
          } else if(xmlHttp.status != 0) {
            this.toError(xmlHttp.status, xmlHttp.statusText);
          }
        }
      }.bind(this)
      xmlHttp.open(type, theUrl, true);
      var send = type === "GET" ? null : params;
      xmlHttp.send(send);
    }
  }
});
