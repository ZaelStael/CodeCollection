import tkinter as tk

root = tk.Tk()

root.geometry("800x500")
root.title("Practice GUI")

label = tk.Label(root, text="Wuz good world?!?", font=('Comic Sans MS', 15))
label.pack(padx=20,pady=20)

textbox = tk.Text(root, height =3, font=("Comic Sans MS", 16))
textbox.pack(padx=10)

#button = tk.Button(root, text ="Click ME!!!", font=('Comic Sans MS', 12))
#button.pack(padx=15, pady=15)*

buttonframe = tk.Frame(root)
buttonframe.columnconfigure(0, weight=1)
buttonframe.columnconfigure(1, weight=1)
buttonframe.columnconfigure(2, weight=1)

btn1 = tk.Button(buttonframe, text="1", font=('Comic Sans MS', 20))
btn1.grid(row=0, column=0, sticky=tk.W+tk.E)

btn2 = tk.Button(buttonframe, text="2", font=('Comic Sans MS', 20))
btn2.grid(row=0, column=1, sticky=tk.W+tk.E)

btn3 = tk.Button(buttonframe, text="3", font=('Comic Sans MS', 20))
btn3.grid(row=0, column=2, sticky=tk.W+tk.E)

btn4 = tk.Button(buttonframe, text="4", font=('Comic Sans MS', 20))
btn4.grid(row=1, column=0, sticky=tk.W+tk.E)

btn5 = tk.Button(buttonframe, text="5", font=('Comic Sans MS', 20))
btn5.grid(row=1, column=1, sticky=tk.W+tk.E)

btn6 = tk.Button(buttonframe, text="6", font=('Comic Sans MS', 20))
btn6.grid(row=1, column=2, sticky=tk.W+tk.E)

buttonframe.pack(fill='x')

anbtn = tk.Button(root, text="Test 0", font=('Comic Sans MS', 18))
anbtn.place(x=266, y=300, height=75, width=266)


#myentry = tk.Entry(root)
#myentry.pack()

root.mainloop()

