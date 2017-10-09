I am doing this kata as though I was doing it for myself. In practice it is likely that the team will not be 
wanting to use type classes, and if that's the case I am happy to code at the level of the team

I am detailing here how I approached the problem

# My first step: read the problem statement, turn it into tests
I know that I am not great at processing textual requirements. My first goal is to turn the text into executable tests
and the revisit the requirements to check that I have covered them

## Setting up build.sbt
I need to add scalatest.

This is only a kata, so I don't really need to worry about multiple modules. I do like to have my versions in one place though

## Writing first tests - Money
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

I've tagged git with 'Money' at this point

## Writing next tests - pricable
I now have an interesting question. How do I determine the price for a product. Is it a look up in a database or an in-memory cache
I am seriously tempted to code up a pricing service as a kleisli with 'Product => Future[Price]' but I think it is too much complexity for now
So I'll instead have a type class that captures the idea of getting a price from an item in the shopping basket. Another reason I like the
typeclass is because I am cheating a little: I've looked ahead, and I know that I will need to have things other than products in the
code base. I will need to have discounts. A third reason is the point I made before about 'constraints liberate'. By locking down my 
Products to things with the typeclass Pricable, I keep single responsibility code, and have clarity over the operations performed on type

Pricable isn't quite a type class because I'm not skilled enough with the type system to curry types, but its close.

I implemented Product as a Pricable and wrote an implementation of the AbstractPricableSpec for Product with Ints

There is a git commit with a tag 'Pricable' at this point

## Writing a shopping cart
I don't want to care at this stage how I get the shopping cart, I just want to be able to hold a list of Pricable things and how many times they occur.
I will have a list of line items. I could just get away with two ints: the count of apples and the count of oranges, but that doesn't
feel good, and is actually harder to work with. I can use list operations on list, but not over 'count of apples, count of oranges'

## Tests for the requirements for step 1
I now need to write a test to say that the shopping basket can add up apples and oranges

While doing this I realise that (hurrah!) I can reuse pricable.

My tests look horrible so I use pimping to make a mini DSL for the test. 
Before:    ShoppingBasket(List(LineItem(orange, 1))).price shouldBe 25
After:     ShoppingBasket(orange, 1).price shouldBe 25

Half way through this I realised that the code would be nicer if the LineItem was pricable as well, so I added some tests for that too

That meant I might as well share the miniDSL and the fixture, so I created the BasketFixture

That done I considered whether to decouple the basket from the line item, but reject the idea: it adds too much complexity for little benefit.
If this was a real application I suspect I would end up doing that, but I'm following YAGNI at the moment

The code at this point is in Git with the tag 'ShoppingBasket'

# Review
At this point I am feeling quite good. I can represent products, and shopping baskets. Re-reading the specification, I am not certain whether
I need to model 'scanning' items. 

## Adding scanning items
This is quite straightforwards though, so I decide to do a simple implementation.
I am going to have a file that holds shopping baskets: each line representing one item
I need a parser that turns the line into a product. 

And now I have a really interesting question: what do I do about exceptions. Is it normal behavior to have non apples and oranges? For
now I code this anomalous behavior as an exception. Later I might move this to some Monad for error handling

# Requirements

## Step 1: Shopping cart

You are building a checkout system for a shop which only sells apples and oranges.
- Apples cost 60p 
- Oranges cost 25p.

Build a checkout system which takes a list of items scanned at the till and outputs the total cost. 
Make reasonable assumptions about the inputs to your solution; for example, many candidates take a list of strings as input

- For example: [ Apple, Apple, Orange, Apple ] => £2.05

## Step 2: Simple offers
The shop decides to introduce two new offers
- buy one, get one free on Apples
- 3 for the price of 2 on Oranges
Update your checkout functions accordingly

