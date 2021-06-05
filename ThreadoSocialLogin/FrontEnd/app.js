let nameText = document.getElementById("name");
let emailText = document.getElementById("email");
let imageField = document.getElementById("image");
let dataBlock = document.getElementById("dataBlock");
let signInBtn = document.getElementById("googleSignIn");
let signOutBtn = document.getElementById("googleSignOut");

/**
 * Function populates signOn
 * @param {*} googleUser
 */
function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  postOauthToken(profile);
  nameText.textContent = profile.getName();
  emailText.textContent = profile.getEmail();
  imageField.src = profile.getImageUrl();
  dataBlock.style.display = "block";
  signInBtn.style.display = "none";
  signOutBtn.style.display = "block";
}
/**
 * Function performs signOut
 */
function signOut() {
  var auth2 = gapi.auth2.getAuthInstance();
  auth2.signOut().then(function () {
    alert("You have signed out successfully");
    dataBlock.style.display = "none";
    signInBtn.style.display = "block";
    signOutBtn.style.display = "none";
    window.localStorage.removeItem("oauthToken");
  });
}
/**
 * Calls oauth API for Google social login
 * @param {*} googleProfile
 */
function postOauthToken(googleProfile) {
  const formData = new FormData();
  formData.append("grant_type", "social");
  formData.append("email", googleProfile.getEmail());
  formData.append("name", googleProfile.getName());
  formData.append("profileImage", googleProfile.getImageUrl());
  formData.append("loginSource", "GOOGLE");

  axios
    .request({
      url: "/oauth/token",
      method: "post",
      baseURL: "http://localhost:8080",
      auth: {
        username: "threado",
        password: "secret",
      },
      data: formData,
    })
    .then(function (response) {
      window.localStorage.setItem("oauthToken", response.data.access_token);
    })
    .catch(function (error) {
      console.log(error, "Error on Authentication");
    });
}
