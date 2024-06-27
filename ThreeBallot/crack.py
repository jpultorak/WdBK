from util import is_legal_triplet, strips_from_triplets, generate_triplets
from pprint import pprint


# For given strip, finds first valid pair, which makes up a valid triplet together
# with that strip
def find_valid_triplet(strip, strip_id, published_strips, used_strips, certain=True):

    valid_triplets = []
    for i in range(len(published_strips)):
        if i == strip_id or used_strips[i]:
            continue
        for j in range(i + 1, len(published_strips)):
            if j == strip_id or used_strips[j]:
                continue
            triplet_ids = [strip_id, i, j]
            if is_legal_triplet(
                [published_strips[strip_id], published_strips[i], published_strips[j]]
            ):
                valid_triplets.append(triplet_ids)

    # if there is only one possibilty, choose it and then proceed
    # print("Valid triplets:")
    # pprint(valid_triplets)

    if 0 < len(valid_triplets) <= 1 or (valid_triplets and not certain):
        id = valid_triplets[0]
        used_strips[id[0]] = used_strips[id[1]] = used_strips[id[2]] = 1
        print(f"Certain guess: {certain}", "Uncracked left:", used_strips.count(0) / 3)
        return [
            published_strips[id[0]],
            published_strips[id[1]],
            published_strips[id[2]],
        ]

    return None


def crack_votes(published_strips):
    cracked_triplets = []
    used_strips = [0] * len(published_strips)

    new_triplet = True
    while new_triplet:
        new_triplet = False
        for id, strip in enumerate(published_strips):

            # value other than 0 means strip is already used
            if used_strips[id] != 0:
                continue

            # otherwise, we try to find matching strips for it
            triplet = find_valid_triplet(strip, id, published_strips, used_strips)
            # if new triplet was found, it might have freed up other triplets
            if triplet is not None:
                cracked_triplets.append(sorted(triplet))
                new_triplet = True

    for id, strip in enumerate(published_strips):
        if used_strips[id] != 0:
            continue
        triplet = find_valid_triplet(
            strip, id, published_strips, used_strips, certain=False
        )
        if triplet is not None:
            cracked_triplets.append(sorted(triplet))

    return sorted(cracked_triplets)


if __name__ == "__main__":
    total_options = 20
    total_voters = 50
    total_races = 3
    triplets = generate_triplets(total_options, total_voters, total_races)
    published_strips = strips_from_triplets(triplets)

    # print("\nTriplets:")
    # pprint(triplets)
    # pprint(published_strips)
    cracked_triplets = crack_votes(published_strips)
    # print("Done cracking")
    # print("\ncracked triplets:")
    # pprint(cracked_triplets)

    total = len(triplets)
    cracked = 0

    for triplet in cracked_triplets:
        if triplet in triplets:
            cracked += 1

    print("Was it cracked?", cracked, total, cracked / total)
