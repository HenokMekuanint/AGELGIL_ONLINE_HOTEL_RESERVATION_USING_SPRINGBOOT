

class Attribute:

    def __init__(self, class_, values):
        self.class_ = class_
        self.values = values

    def __format_breakpoint(self, bp):
        sp = self.class_.split("-")
        return f"{sp[0]}-{bp}-{'-'.join(sp[1:])}"

    def generate_values(self):
        return "\n\t" + "\n\t".join(self.values) + "\n"

    def generate_breakpoint(self, bp):
        return f".local-{self.__format_breakpoint(bp)} {{{self.generate_values()}}}"

    def generate_raw(self):
        return f".local-{self.class_} {{{self.generate_values()}}}"


class Generator:

    def __init__(self, attributes, small_query, medium_query, large_query):
        self.attributes = attributes
        self.small_query = small_query
        self.medium_query = medium_query
        self.large_query = large_query

    def generate(self):
         return (
                 "\n\n".join([attribute.generate_raw() for attribute in self.attributes]) + 
                 f"\n\n\n{self.small_query}{{\n\n" + "\n\n".join([attribute.generate_breakpoint("sm") for attribute in self.attributes]) + "\n\n}" +
                 f"\n\n\n{self.medium_query}{{\n\n" + "\n\n".join([attribute.generate_breakpoint("md") for attribute in self.attributes]) + "\n\n}" +
                 f"\n\n\n{self.large_query}{{\n\n" + "\n\n".join([attribute.generate_breakpoint("lg") for attribute in self.attributes]) + "\n\n}"
                 )
         




ATTRIBUTES = [
        Attribute("w-1", ["width: 1em;"]),
        Attribute("w-2", ["width: 2em;"]),
        Attribute("w-3", ["width: 3em;"]),
        Attribute("w-4", ["width: 4em;"]),
        Attribute("w-5", ["width: 5em;"]),

        Attribute("h-1", ["height: 1em;"]),
        Attribute("h-2", ["height: 2em;"]),
        Attribute("h-3", ["height: 3em;"]),
        Attribute("h-4", ["height: 4em;"]),
        Attribute("h-5", ["height: 5em;"]),

        Attribute("br-0", ["border-radius: 0em !important;"]),
		Attribute("br-1", ["border-radius: 1em !important;"]),
        Attribute("br-2", ["border-radius: 2em !important;"]),
        Attribute("br-3", ["border-radius: 4em !important;;"]),
        Attribute("br-4", ["border-radius: 8em !important;;"]),
        Attribute("br-5", ["border-radius: 16em !important;;"]),

		Attribute("mt-6", ["margin-top: 4em;"]),
		Attribute("mt-7", ["margin-top: 8em;"]),
		Attribute("mt-8", ["margin-top: 16em;"]),
		Attribute("mt-9", ["margin-top: 32em;"]),
		Attribute("mt-10", ["margin-top: 64em;"]),

		Attribute("mb-6", ["margin-bottom: 4em;"]),
		Attribute("mb-7", ["margin-bottom: 8em;"]),
		Attribute("mb-8", ["margin-bottom: 16em;"]),
		Attribute("mb-9", ["margin-bottom: 32em;"]),
		Attribute("mb-10", ["margin-bottom: 64em;"]),

		Attribute("mr-6", ["margin-right: 4em;"]),
		Attribute("mr-7", ["margin-right: 8em;"]),
		Attribute("mr-8", ["margin-right: 16em;"]),
		Attribute("mr-9", ["margin-right: 32em;"]),
		Attribute("mr-10", ["margin-right: 64em;"]),

		Attribute("ml-6", ["margin-left: 4em;"]),
		Attribute("ml-7", ["margin-left: 8em;"]),
		Attribute("ml-8", ["margin-left: 16em;"]),
		Attribute("ml-9", ["margin-left: 32em;"]),
		Attribute("ml-10", ["margin-left: 64em;"]),


		Attribute("my-6", ["margin-top: 4em;", "margin-bottom: 4em;"]),
		Attribute("my-7", ["margin-top: 8em;", "margin-bottom: 8em;"]),
		Attribute("my-8", ["margin-top: 16em;", "margin-bottom: 16em;"]),
		Attribute("my-9", ["margin-top: 32em;", "margin-bottom: 32em;"]),
		Attribute("my-10", ["margin-top: 64em;", "margin-bottom: 64em;"]),

		Attribute("mx-6", ["margin-left: 4em;", "margin-right: 4em;"]),
		Attribute("mx-7", ["margin-left: 8em;", "margin-right: 8em;"]),
		Attribute("mx-8", ["margin-left: 16em;", "margin-right: 16em;"]),
		Attribute("mx-9", ["margin-left: 32em;", "margin-right: 32em;"]),
		Attribute("mx-10", ["margin-left: 64em;", "margin-right: 64em;"]),

		Attribute("pt-6", ["padding-top: 4em;"]),
		Attribute("pt-7", ["padding-top: 8em;"]),
		Attribute("pt-8", ["padding-top: 16em;"]),
		Attribute("pt-9", ["padding-top: 32em;"]),
		Attribute("pt-10", ["padding-top: 64em;"]),

		Attribute("pb-6", ["padding-bottom: 4em;"]),
		Attribute("pb-7", ["padding-bottom: 8em;"]),
		Attribute("pb-8", ["padding-bottom: 16em;"]),
		Attribute("pb-9", ["padding-bottom: 32em;"]),
		Attribute("pb-10", ["padding-bottom: 64em;"]),

		Attribute("pr-6", ["padding-right: 4em;"]),
		Attribute("pr-7", ["padding-right: 8em;"]),
		Attribute("pr-8", ["padding-right: 16em;"]),
		Attribute("pr-9", ["padding-right: 32em;"]),
		Attribute("pr-10", ["padding-right: 64em;"]),

		Attribute("pl-6", ["padding-left: 4em;"]),
		Attribute("pl-7", ["padding-left: 8em;"]),
		Attribute("pl-8", ["padding-left: 16em;"]),
		Attribute("pl-9", ["padding-left: 32em;"]),
		Attribute("pl-10", ["padding-left: 64em;"]),


		Attribute("py-6", ["padding-top: 4em;", "padding-bottom: 4em;"]),
		Attribute("py-7", ["padding-top: 8em;", "padding-bottom: 8em;"]),
		Attribute("py-8", ["padding-top: 16em;", "padding-bottom: 16em;"]),
		Attribute("py-9", ["padding-top: 32em;", "padding-bottom: 32em;"]),
		Attribute("py-10", ["padding-top: 64em;", "padding-bottom: 64em;"]),

		Attribute("px-6", ["padding-left: 4em;", "padding-right: 4em;"]),
		Attribute("px-7", ["padding-left: 8em;", "padding-right: 8em;"]),
		Attribute("px-8", ["padding-left: 16em;", "padding-right: 16em;"]),
		Attribute("px-9", ["padding-left: 32em;", "padding-right: 32em;"]),
		Attribute("px-10", ["padding-left: 64em;", "padding-right: 64em;"]),

		Attribute("fs-1", ["font-size: 500% !important;"]),
		Attribute("fs-2", ["font-size: 300% !important;"]),
		Attribute("fs-3", ["font-size: 200% !important;"]),
		Attribute("fs-4", ["font-size: 150% !important;"]),
		Attribute("fs-5", ["font-size: 75% !important;"]),
		Attribute("fs-0", ["font-size: 100% !important;"]),

		Attribute("zi-1", ["z-index:5000 !important;"]),


        ]

if __name__ == "__main__":
    export = open("local.css", "w")
    print(Generator(ATTRIBUTES, "@media(max-width: 768px)", "@media(min-width: 769px) and (max-width: 1024px)", "@media(min-width: 1025px)").generate(), file=export)
