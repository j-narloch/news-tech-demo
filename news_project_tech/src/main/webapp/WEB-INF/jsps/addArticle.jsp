<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/css/style.css">
  <title>Document</title>
</head>
<body>
  <div class="wrapper">
    <header class="header">
     <%@ include file="headerBar.jsp" %>
    </header>
    <main class="main">
      <div class="main__title">Add your article</div>
      <div class="main__login-container">
      <div class="main__login">
        <form 
          action="addArticle" 
          method="POST" 
          class="form"
          enctype="multipart/form-data"
        >
          <label for="articleName">Add title:</label><br>
          <input 
            type="text" 
            id="articleName" 
            name="articleName"
            class="second"
            placeholder="Title"
          ><br>
          <label for="description">Description:</label><br>
          <textarea 
            id="description" 
            name="description" 
            rows="7" 
            cols="35"
            class="textarea"
            class="second"
            placeholder="">
          </textarea><br>
          <label for="articleTextOne" class="paragraph">First paragraph:</label><br>
          <p class="warning">Add no more than 10 000 characters</p>
          <textarea 
            id="articleTextOne" 
            name="articleTextOne" 
            rows="7" 
            cols="35"
            class="textarea"
            class="second">
          </textarea><br>
          <label for="articleTextTwo" class="paragraph">Second paragraph:</label><br>
          <p class="warning">Add no more than 10 000 characters</p>
          <textarea 
            id="articleTextTwo" 
            name="articleTextTwo" 
            rows="7" 
            cols="35"
            class="textarea"
            class="second">
          </textarea><br>
          <label for="articleTextThree" class="paragraph">Third paragraph:</label><br>
          <p class="warning">Add no more than 10 000 characters</p>
          <textarea 
            id="articleTextThree"
            name="articleTextThree" 
            rows="7" 
            cols="35"
            class="textarea"
            class="second">
          </textarea><br>
          <label for="articleTextFour" class="paragraph">Fourth paragraph:</label><br>
          <p class="warning">Add no more than 10 000 characters</p>
          <textarea 
            id="articleTextFour" 
            name="articleTextFour" 
            rows="7" 
            cols="35"
            class="textarea"
            class="second">
          </textarea><br>
          <label for="category">Choose category:</label><br>
            <select name="category" id="category">
            <option value="IOS">IOS</option>
            <option value="Android">Android</option>
            <option value="IT">IT</option>
            <option value="Global">Global</option>
            </select>
          <br>
          <div class="main__login-button">
          <label>Photo: </label>
            <input 
              type="file" 
              name="image" 
              accept="image/png, image/jpeg, image/jpg"
            >
            <input type="submit" value="Add article">
          </div>
        </form>
      </div>
      </div>
    </main>
    <footer class="footer">
    <%@ include file="footerBar.jsp" %>
    </footer>
  </div>
</body>
</html>