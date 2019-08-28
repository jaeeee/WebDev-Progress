Moon.use(MoonRouter);

   Moon.component("Home", {
       template:
           `<div>
                <h1>Home</h1>
                <p>This is the homepage of your application! Check out the calculator!</p>
            </div>`
   });

   Moon.component("Calculator", {
       data: () => ({
           number1: 1,
           number2: 2
       }),
       template:
           `<div>
                <h1>Calculator</h1>
                <input type="number" m-model="number1">
                +
                <input type="number" m-model="number2">
                =
                <span>{{total}}</span>
            </div>`,
        computed: {
           total: {
               get: function() {
                 return +this.get("number1") + +this.get("number2");
               }
           }
       }
   });

   const router = new MoonRouter({
       map: {
           "/": "Home",
           "/calculator": "Calculator"
       }
   });

   new Moon({
       el: "#app",
       router: router
   });
