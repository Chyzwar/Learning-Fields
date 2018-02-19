
class Promise{
  constructor(executor){
    this.status = "pending"

    setTimeout(
      executor(
        this.resolve.bind(this),
        this.reject.bind(this),
      ),
      1
    );
  }

  getThenCallbacks(){
    return this.thenCallbacks || (this.thenCallbacks = []);
  }

  then(callback){
    this
      .getThenCallbacks()
      .push(callback);

    return this;
  }

  getCatchCallbacks(){
    return this.catchCallbacks || (this.catchCallbacks = []);
  }

  catch(callback){
    this
      .getCatchCallbacks()
      .push(callback);

    return this;
  }

  resolve(value){
    this.status = "resolved";

    if(this.thenCallbacks){
      const iterator = this.thenCallbacks.values();

      const next = (value) => {
        const callback = iterator.next()

        if(callback){
          const result = callback(value);

          if(result instanceof Promise){
            result.then((value) => next(value));
          }
          else {
            next(result);
          }
        }
      }
      next(value);
    }
  }

  reject(error){
    this.status = "rejected";

    if(this.catchCallbacks){
      const iterator = this.catchCallbacks.values();

      const next = (value) => {
        const callback = iterator.next()

        if(callback){
          const result = callback(value);

          if(result instanceof Promise){
            result.then((value) => next(value));
          }
          else {
            if(value instanceof Error){
              next(value);
            }
          }
        }
      }
      next(value);
    }
  }

  static resolve(value){
    if(value instanceof Promise){

    }
  }

  static reject(){

  }
}
