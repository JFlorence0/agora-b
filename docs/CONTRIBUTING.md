# Contributing to Agora 🏛️

Thank you for your interest in contributing to **Agora**! 🚀  
We welcome all contributions, from bug fixes and documentation improvements to new feature implementations.

---

## 🛠 How to Contribute

1. **Fork the Repository**  
   Click the **"Fork"** button at the top of the [GitHub repo](https://github.com/JFlorence0/agora-b).

2. **Clone Your Fork**
   ```sh
   git clone https://github.com/YOUR_GITHUB_USERNAME/agora-b.git
   cd agora-b

3. **Create a New Branch**
    git checkout -b feature-your-feature-name

4. **Make Your Changes**
    Follow the code style and naming conventions.
    Leave comments in your code—unclear code may be skipped over by maintainers.
    Test your changes locally before submitting.

5. **Commit & Push**
    git add .
    git commit -m "Added [feature-name]"
    git push origin feature-your-feature-name

6. **Open a Pull Request**
    Go to the original repository.
    Click "New Pull Request".
    Select your branch and submit.

📝 Code Guidelines

1. **Write Clear & Maintainable Code**
    Use meaningful variable and function names.
    Keep functions small and focused.
    Follow the existing project structure.

2. **Comment Your Code**
    Code without comments may be skipped over by maintainers!
    Explain complex logic.
    Leave notes for maintainers or future contributors.

    **Example of good commenting:**
    // ✅ GOOD: Explains the purpose
    /*
    Fetches all active users who have voted in the last 30 days.
    */
    public List<User> getActiveVoters() { ... }

    **Example of bad commenting:**
    // ❌ BAD: No explanation
    public List<User> getActiveVoters() { ... }

3. **Ensure Code Readability**
    Use consistent indentation.
    Write meaningful commit messages.
    Avoid unnecessary complexity.

4. **Test Before Submitting**
    Run the project and test your changes before making a pull request:
    ./gradlew test

**Code of Conduct**
    Be respectful and inclusive.
    Ensure your code follows best practices.
    Well-documented code is more likely to be merged.

**Need Help?**
    Be respectful and inclusive.
    Ensure your code follows best practices.
    Well-documented code is more likely to be merged.
    If you have questions, open an issue in the repository.

**🚀 Happy coding! 🏛️**