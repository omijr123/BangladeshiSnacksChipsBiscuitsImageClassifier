
# 🍪 Bangladeshi Snacks, Chips & Biscuits Image Classifier

An intelligent Android application that uses TensorFlow Lite and machine learning to identify 60+ popular Bangladeshi snacks, chips, and biscuits through real-time camera image classification.

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![TensorFlow](https://img.shields.io/badge/TensorFlow-FF6F00?style=for-the-badge&logo=tensorflow&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white)

## 📱 App Preview

<div align="center">

-- Home page

![pic1](https://i.postimg.cc/90pZV95w/1.jpg)

-- Property+Listing

![pic2](https://i.postimg.cc/52jLdjPN/2.jpg)

-- Admin Dashboard

![pic3](https://i.postimg.cc/52jLdjPN/2.jpg)


</div>

## ✨ Features

- **📸 Real-time Image Capture** - Use your camera to instantly capture snack images
- **🤖 AI-Powered Classification** - TensorFlow Lite model trained on 60+ Bangladeshi snacks
- **🎯 Accurate Results** - Displays top 5 predictions with confidence percentages
- **🏪 Comprehensive Database** - Covers popular brands like Sun Chips, Bombay Sweets, Kurkure, Pran, and more
- **📊 Detailed Analytics** - Shows confidence levels for all detected items
- **🎨 Modern UI** - Clean, material design interface with smooth user experience

## 🛠️ Technologies Used

- **Android SDK** - Native Android development
- **TensorFlow Lite** - On-device machine learning
- **Java** - Primary programming language
- **Material Design** - Modern UI components
- **CameraX** - Camera integration and image processing

## 📦 Installation

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24+
- Minimum API Level 24 (Android 7.0)

### Build Steps
1. Clone the repository:
2.  git clone https://github.com/omijr123/BangladeshiSnacksChipsBiscuitsImageClassifier.git

3. Open the project in Android Studio

4. Sync the project with Gradle files

5. Build and run on your Android device or emulator

## 🎯 Supported Snack Classes

The model can identify 60 different Bangladeshi snacks across these categories:

### 🥨 Chips & Crisps
- Sun Chips (Mix Masala, Garlic & Chilli, Tomato Tango, Salt & Pepper)
- Bombay Sweets (Potato Cracker, Alooz series, Nachoz, Pasta Chips)
- Kurkure (American Style Cream & Onion, Chilli Chatka, Masala Munch)
- Lays (Thai Style Spicy Chicken, American Style Cream & Onion, Spanish Tomato Tango)
- Pringles (Sour Cream & Onion, Original)
- Haque (Potato Chips, Pillow Chips, Tarzan Chips)

### 🍪 Biscuits & Cookies
- Bisk Club Shero Biscuit
- Olympic (Energy Plus, Lexus Vegetable Crackers, Nutty Real Peanut)
- Haque Mr. Cookie Butter Coconut
- Pran Potata Spicy Biscuit
- Dekko Black & Brown Chocolate Chips Cookies

### 🍦 Ice Cream & Frozen Treats
- Igloo (Cup Vanilla, Stick Shell & Core, Premium Macho, Container Mango Fresh)

### 🥜 Chanachur & Mixes
- Ruchi (BBQ Chanachur, Jhal Chanachur)
- Bombay Sweets Dalmoth Chanachur

### 📋 Complete Class List
<details>
<summary>View all 60 snack classes</summary>
  
0 Sun Chips Mix Masala
1 Sun Chips Garlic & Chilli
2 Bombay Sweets Potato Cracker (Free Surprise Toy)
3 Bombay Sweets Pasta Chips Shell Chaat Flavour
4 Bombay Sweets Korntos Chaat Masti
5 Bombay Sweets Alooz Charcoal BBQ Chips
6 Bombay Sweets Alooz Waves Phuchka
7 Sun Chips Tomato Tango
8 Cheese Puff
9 Bombay Sweets Cheese Ball Cheezee Corn Snacks
10 Pran Potato Cracker
11 Bombay Sweets Ring Chips
12 Ifad Eggy Stix Bar-B-Q Chips
13 Kurkure American Style Cream & Onion Chips
14 Haque Potato Chips
15 Bombay Sweets Potato Crackers
16 Bombay Sweets Nachoz Chutney & Lime
17 Bombay Sweets Alooz Spanish Tomato Flavor
18 Sun Chips Salt & Pepper
19 Kurkure Chilli Chatka Chips
20 Bombay Sweets Mr. Chicken Chips
21 Ifad Eggy Pillow Bar-B-Q Chips
22 Pringles Sour Cream & Onion Potato Chips
23 Lay's Thai Style Spicy Chicken Potato Chips
24 Kurkure Masala Munch Chips
25 Ruchi BBQ Potato Crackers
26 Bombay Sweets Alooz Waves Hot Flavour Potato Chips
27 Bombay Sweets Alooz Red Chilli Chatka
28 Bombay Sweets Nachoz Spicy Nacho Cheese
29 Bombay Sweets Alooz Magic Masala
30 Bombay Sweets Pasta Chips Shell Chutney Flavour
31 Haque Pillow Chips
32 Haque Tarzan Chips
33 ACI Fun Fresh Fry Potato Pallets
34 Pringles Original Potato Chips
35 Meridian Real Thai Chicken Chips
36 Detos Chicken Wings Chips 
37 Curl
38 Mr.Twist Tasty & Crispy Potato Chips
39 Potato Crackers
40 Lays American Style Cream & Onion Chips
41 Lays Spanish Tomato Tango Chips
42 Yokozona Twisty Goodness Crackers
43 Meridian Crispy Chicken Sticks
44 Poppers Corn Coconut Crackers
45 Dekko Black & Brown Chocolate Chips Cookies 
46 Ruchi BBQ Chanachur
47 Bombay Sweets Dalmoth Chanachur
48 Ruchi Jhal Chanachur
49 Class 50
50 Igloo Cup Vanilla
51 Igloo Stick Shell & Core
52 Igloo Premium Macho
53 Igloo Container Mango Fresh 
54 Bisk Club Shero Biscuit
55 Olympic Energy Plus Biscuits
56 Olympic Lexus Vegetable Crackers Biscuits
57 Haque Mr. Cookie Butter Coconut Biscuit
58 Olympic Nutty Real Peanut Biscuit
59 Pran Potata Spicy Biscuit

</details>

## 🚀 Usage

1. **Launch the app** on your Android device
2. **Grant camera permissions** when prompted
3. **Point your camera** at any Bangladeshi snack
4. **Capture the image** using the camera button
5. **View results** showing the identified snack and confidence levels
6. **Analyze predictions** with top 5 matches and their confidence percentages

## 🏗️ Project Structure

app/
├── src/main/
│   ├── java/.../MainActivity.java     # Main application logic
│   ├── res/layout/activity_main.xml   # User interface
│   ├── ml/model_unquant.tflite        # TensorFlow Lite model
│   └── res/values/strings.xml         # String resources

## 🤝 Contributing

We welcome contributions to improve the app! Here's how you can help:

1. Fork the repository
2. Create a feature branch (git checkout -b feature/AmazingFeature)
3. Commit your changes (git commit -m 'Add some AmazingFeature')
4. Push to the branch (git push origin feature/AmazingFeature)
5. Open a Pull Request

### Areas for Improvement
- Add more snack classes
- Improve model accuracy
- Enhance UI/UX design
- Add offline functionality
- Support for multiple languages

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- TensorFlow Lite team for the machine learning framework
- Android developer community for continuous support
- All contributors who help improve this project

## 📞 Contact

For questions, suggestions, or collaborations:
- GitHub: [@omijr123](https://github.com/omijr123)
- Project Link: [https://github.com/omijr123/BangladeshiSnacksChipsBiscuitsImageClassifier](https://github.com/omijr123/BangladeshiSnacksChipsBiscuitsImageClassifier)

