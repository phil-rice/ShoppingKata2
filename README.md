I am doing this kata as though I was doing it for myself. In practice it is likely that the team will not be 
wanting to use type classes, and if that's the case I am happy to code at the level of the team

I am detailing here how I approached the problem

# My first step: read the problem statement, turn it into tests
I know that I am not great at processing textual requirements. My first goal is to turn the text into executable tests
and the revisit the requirements to check that I have covered them

## Setting up build.sbt
I need to add scalatest.

This is only a kata, so I don't really need to worry about multiple modules. I do like to have my versions in one place though

## Writing first tests
I know I need to worry about things being bought. I don't want to make apples and oranges classes as there would be in
a real shopping application tens of thousands of them. I know these things are going to have a price. The bad news now
is that price implies money. Money is a really hard concept in Java/Scala. Do we need to extend this kata into currencies or
multiple currencies? Given the YAGNI principle I'm not going to code that up. This would be a good place to show off path
dependant types, but I'd rather keep it simple for now.

Nowadays where possible I use type classes to isolate business logic from the implementation of the data, so I will be implementing
products as a type class, and implementing money as another. If this gets too cumbersome I will simplify it later. Usually I 
find that this focus on single responsibility works out well for me

I started with the MoneySpec. Money is a type class. It has the properties of a Monoid, so I implement a test for that first, and add 
the helper method '|*|' which will does an integer multiplier. I know I will need it because my basket will end up being 'N apples and M oranges'.
MoneySpec is abstract, so I implement an IntMoneySpec so that I can use Ints for money in my tests

I coded up Money, adding a pimper that implements |+| and |*|

This means hereafter I don't need to worry about how Money will be implemented, and I have captured the activities that Money does. 
I am a big fan of the idea that 'constraints liberate' and this is a good example of that






## Step 1: Shopping cart

You are building a checkout system for a shop which only sells apples and oranges.
- Apples cost 60p 
- Oranges cost 25p.

Build a checkout system which takes a list of items scanned at the till and outputs the total cost. Make reasonable assumptions about the inputs to your solution; for example, many candidates take a list of strings as input

- For example: [ Apple, Apple, Orange, Apple ] => £2.05

## Step 2: Simple offers
The shop decides to introduce two new offers
- buy one, get one free on Apples
- 3 for the price of 2 on Oranges
Update your checkout functions accordingly

