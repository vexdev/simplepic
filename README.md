# SimplePic - Picture taking PoC

Simple software that can take pictures and show a gallery of the taken pictures.

## Developer notes

### View Architecture

For the purpose of this evaluation, I'm simply going to stick to the architecture suggested by AOSP
in [The JetPack Guide](https://developer.android.com/jetpack/guide).
However please note that I really believe that every application should be carefully architected
according to its very specific needs and that so far I've been successfully using various different 
architectures not necessarily relying on third party libraries.


### User Experience

I would have pushed for having the gallery as the main screen, fitting well with the floating action
button, however in this case I decided to respect the request of the evaluators to have the gallery 
as a separate screen.
