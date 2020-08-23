# SimplePic - Picture taking PoC

Simple software that can take pictures and show a gallery of the taken pictures.

## Developer notes

### View Architecture

For the purpose of this evaluation, I'm simply going to stick to the architecture suggested by AOSP
in [The JetPack Guide](https://developer.android.com/jetpack/guide).
However please note that I really believe that every application should be carefully architected
according to its very specific needs and that so far I've been successfully using various different 
architectures not necessarily relying on third party libraries.
Not everywhere the full ViewModel/Repository separation was implemented, but only where it made 
sense.

Note that up navigation was purposely not implemented. Back navigation in this simple hierarchy
should suffice.

### User Experience

I would have pushed for having the gallery as the main screen, fitting well with the floating action
button, however in this case I decided to respect the request of the evaluators to have the gallery 
as a separate screen.

### Usage of LiveData

I'm not a big fan of LiveData and would normally make use of RxJava. That however requires some more
thinking about lifecycle safety. I decided to use LiveData instead as that comes provided already.

### Testing

Many more tests could be developed, I choose to setup very few because adding more would require
more mocking to be put in place, and possibly additional technologies such as dependency injection 
which I decided not to use for this time-limited implementation.

Yet I demonstrated the approach to Unit testing as well as Instrumentation (UI) Testing.
