from flask import Flask, jsonify, request, render_template
# My classes
from classes.book_data import BookData
from classes.book_analysis import BookAnalysis
from classes.browser_analysis import BrowserAnalysis
from classes.user_analysis import UserAnalysis

app = Flask(__name__)


@app.route('/')
def home():
    return render_template('home.html')


@app.route('/task-1')
def task_1():
    return render_template('task-1.html')


@app.route('/task-2')
def task_2():
    return render_template('task-2.html')


@app.route('/api/task-2', methods=['POST'])
def api_task_2():
    """Perform task from task 2 """
    uuid = request.form['uuid']
    bs = BookAnalysis(app.dt)
    try:
        #By Countries
        result_countries = bs.counries_by_book(uuid)
        print("Views by country and Document UUID")
        print(result_countries)
        bs.counries_by_book_plot(result_countries, uuid)
        # By Continent
        print("Views by continet and Document UUID")
        result_continent = bs.continent_by_book(result_countries, uuid)
        bs.continet_by_book_plot(result_continent, uuid)
        print(result_continent)
        return jsonify(result_countries)
    except Exception, err:
        return jsonify({'exception': str(err)})


@app.route('/task-3')
def task_3():
    """Create a View for task 3 """
    return render_template('task-3.html')


@app.route('/api/task-3', methods=['POST'])
def api_task_3():
    """Perform task from task 3 """
    ba = BrowserAnalysis(app.dt)
    try:
        ba.browser_usage_plot()
        ba.general_usage_plot()
        return jsonify({"succesfully": {}})
    except Exception, err:
        return jsonify({'exception': str(err)})


@app.route('/task-4')
def task_4():
    """Create view for Task 4"""
    return render_template('task-4.html')


@app.route('/api/task-4', methods=['POST'])
def api_task_4():
    """Perform task from task 4 """
    ra = UserAnalysis(app.dt)
    try:
        number = int(request.form['numbers'])
        readers_data = ra.best_readers_data(number)
        print("Most active users data: ")
        print(readers_data)
        return jsonify({"succesfully": readers_data})
    except Exception, err:
        return jsonify({'exception': str(err)})


@app.route('/task-5')
def task_5():
    return render_template('task-5.html')


@app.route('/api/task-5a', methods=['POST'])
def api_task_5a():
    """Perform task from task 5 for books """
    ra = UserAnalysis(app.dt)
    try:
        book_uuid = request.form['book_uuid']
        visitors_books_alike = ra.book_visitors_alike(book_uuid)
        print("Similar books based on user reader")
        print(visitors_books_alike)
        book_alike_sorted = ra.book_alike_sorted(book_uuid, ra.sorter)
        print("Sorted books alike by readership")
        print(book_alike_sorted)
        return jsonify({"book_alike": visitors_books_alike, "alike_sorted": book_alike_sorted})
    except Exception, err:
        return jsonify({'exception': str(err)})


@app.route('/api/task-5b', methods=['POST'])
def api_task_5b():
    """Perform task from task 5  for users"""
    ra = UserAnalysis(app.dt)
    try:
        user_uuid = request.form['user_uuid']
        user_books_alike = ra.user_visitors_alike(user_uuid)
        print("Similar users based on common book")
        print(user_books_alike)
        user_book_alike_sorted = ra.users_alike_sorted(user_uuid, ra.sorter)
        print("Similar users sorted by readership")
        print(user_book_alike_sorted)
        return jsonify({"user_alike": user_books_alike, "alike_sorted": user_book_alike_sorted})
    except Exception, err:
        return jsonify({'exception': str(err)})


@app.after_request
def add_header(response):
    """
    Add headers to both force latest IE rendering engine or Chrome Frame,
    and also to cache the rendered page for 10 minutes.
    Turn off caching
    """
    response.headers['X-UA-Compatible'] = 'IE=Edge,chrome=1'
    response.headers['Cache-Control'] = 'public, max-age=0'
    return response


if __name__ == '__main__':
    """Toghether with starting Flask load Dataframe"""
    # Load DataFrame
    bd = BookData('data/issuu_full.json')
    app.dt = bd.build_data_frame()
    app.run(debug=True)
